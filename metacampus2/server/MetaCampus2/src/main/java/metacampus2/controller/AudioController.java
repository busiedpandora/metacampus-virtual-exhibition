package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.IAudioService;
import metacampus2.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class AudioController extends MainController {

    protected static final String MODEL_AUDIOS = "audios";
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

        model.addAttribute(ImageController.MODEL_IMAGES, imageService.getAllImages());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_AUDIO_FORM;
    }

    @PostMapping(CTRL_AUDIOS + CTRL_NEW)
    public String newAudio(Audio audio, @RequestParam("audioFile") MultipartFile audioFile,
                           @RequestParam(value = "imageToAdd") Image image) throws IOException {
        if(audioFile != null && !audioFile.isEmpty()) {
            String audioName = audioFile.getOriginalFilename();
            for(DisplayPanel displayPanel : image.getDisplayPanels()) {
                String imageNameWithoutExtension = image.getName().substring(0, image.getName().lastIndexOf('.'));
                File imageDirectory = new File(METAVERSES_PATH + displayPanel.getMetaverse().getUrlName() +
                        SEPARATOR + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR
                        + IMAGES_PATH + imageNameWithoutExtension);

                if(!imageDirectory.exists()) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW + "?error";
                }

                File audioDirectory = new File(imageDirectory.getPath() + SEPARATOR + AUDIO_PATH);

                if(!audioDirectory.exists()) {
                    if(!audioDirectory.mkdirs()) {
                        return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW + "?error";
                    }
                }

                Path audioPath = Path.of(audioDirectory.getPath() + SEPARATOR + audioName);

                try {
                    Files.copy(audioFile.getInputStream(), audioPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW + "?error";
                }
            }

            if(image.getAudio() != null) {
                audioService.removeAudio(image.getAudio());
            }

            audio.setName(audioName);
            audio.setImage(image);
            audioService.addNewAudio(audio);

            return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS + CTRL_NEW + "?error";
    }
}
