package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.IAudioService;
import metacampus2.service.IImageService;
import metacampus2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class AudioController extends MainController {

    protected static final String MODEL_AUDIOS = "audios";
    protected static final String MODEL_AUDIO = "audio";
    protected static final String VIEW_AUDIOS = "audios";
    protected static final String VIEW_AUDIO_FORM = "audio-form";

    private IAudioService audioService;
    private IImageService imageService;
    private IUserService userService;

    @Autowired
    public AudioController(IAudioService audioService, IImageService imageService, IUserService userService) {
        this.audioService = audioService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @GetMapping(CTRL_AUDIOS)
    public String audios(Model model){
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.AUDIO);

        model.addAttribute(MODEL_AUDIOS, audioService.getAllAudios());

        return VIEW_AUDIOS;
    }

    @GetMapping(CTRL_AUDIOS + CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
    public String newAudioForm(Model model, @RequestParam(value = "error", required = false) String error){

        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.AUDIO);

        Audio audio = new Audio();
        model.addAttribute(MODEL_AUDIO, audio);
        model.addAttribute(MODEL_ON_EDIT, false);

        model.addAttribute(MODEL_TITLE, "New audio:");
        model.addAttribute(MODEL_FORM_HREF, "/resources/audios/new");

        model.addAttribute(ImageController.MODEL_IMAGES, imageService.getAllImages());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_AUDIO_FORM;
    }

    @GetMapping(CTRL_AUDIOS + "/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('CREATOR')")
    public String editAudioForm(Model model, @PathVariable("id") Long id,
                                @RequestParam(value = "error", required = false) String error){

        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.AUDIO);

        Audio audio = audioService.getAudioById(id);
        model.addAttribute(MODEL_AUDIO, audio);
        model.addAttribute(MODEL_ON_EDIT, true);

        model.addAttribute(MODEL_TITLE, "Edit audio:");
        model.addAttribute(MODEL_FORM_HREF, "/resources/audios/" + id + "/edit");

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_AUDIO_FORM;
    }

    @PostMapping(CTRL_AUDIOS + CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
    public String newAudio(Audio audio, @RequestParam("audioFile") MultipartFile audioFile,
                           @RequestParam(value = "imageToAdd") Image image) {
        if(audioFile != null && !audioFile.isEmpty()) {
            if(audioService.getAudioByTitle(audio.getTitle()) != null) {
                return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW + "?error=an audio with this title already exists";
            }

            String audioFileName = audioFile.getOriginalFilename();
            for(DisplayPanel displayPanel : image.getDisplayPanels()) {
                if(!audioService.createFile(audio, audioFile, image, displayPanel)) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW
                            + "?error=error while copying audio file into resources folder";
                }
            }

            if(image.getAudio() != null) {
                audioService.removeAudio(image.getAudio());
            }

            audio.setCreator(userService.getUserLogged());
            audio.setFileName(audioFileName);
            audio.setImage(image);
            audioService.addNewAudio(audio);

            return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW + "?error=audio file is null or empty";
    }

    @PostMapping(CTRL_AUDIOS + "/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('ADMIN') || hasRole('CREATOR') && #audio.creator.username == authentication.name")
    public String editAudio(@PathVariable("id") Long id, @P("audio") Audio audio) {
        Audio audioByTitle = audioService.getAudioByTitle(audio.getTitle());
        if(audioByTitle == null || Objects.equals(audioByTitle.getId(), id)) {
            Image image = audio.getImage();
            Audio audioById = audioService.getAudioById(id);
            for(DisplayPanel displayPanel : image.getDisplayPanels()) {
                if(!audioService.renameFile(audioById.getTitle(), audio, image, displayPanel)) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + "/" + id + CTRL_EDIT
                            + "?error=error occured while renaming the audio file";
                }
            }

            audioService.editAudio(audio);

            return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + "/" + id + CTRL_EDIT
                + "?error=an audio with this title already exists";
    }
}
