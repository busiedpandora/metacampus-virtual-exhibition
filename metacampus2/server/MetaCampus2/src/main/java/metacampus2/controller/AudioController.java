package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.IAudioService;
import metacampus2.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class AudioController extends MainController {

    protected static final String MODEL_AUDIOS = "audios";
    protected static final String MODEL_AUDIO = "audio";
    protected static final String VIEW_AUDIOS = "audios";
    protected static final String VIEW_AUDIO_FORM = "audio-form";

    private IAudioService audioService;
    private IImageService imageService;

    @Autowired
    public AudioController(IAudioService audioService, IImageService imageService) {
        this.audioService = audioService;
        this.imageService = imageService;
    }

    @GetMapping(CTRL_AUDIOS)
    public String audios(Model model){
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.AUDIO);

        model.addAttribute(MODEL_AUDIOS, audioService.getAllAudios());

        return VIEW_AUDIOS;
    }

    @GetMapping(CTRL_AUDIOS + CTRL_NEW)
    public String audioForm(Model model, @RequestParam(value = "error", required = false) String error){

        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.AUDIO);

        Audio audio = new Audio();
        model.addAttribute(MODEL_AUDIO, audio);

        model.addAttribute(ImageController.MODEL_IMAGES, imageService.getAllImages());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_AUDIO_FORM;
    }

    @PostMapping(CTRL_AUDIOS + CTRL_NEW)
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

            audio.setFileName(audioFileName);
            audio.setImage(image);
            audioService.addNewAudio(audio);

            return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW + "?error=audio file is null or empty";
    }
}
