package metacampus2.controller;

import metacampus2.model.Audio;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.service.IAudioPanelService;
import metacampus2.service.IAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class AudiosController extends MainController {

    protected static final String MODEL_AUDIOS = "audios";
    protected static final String VIEW_AUDIOS = "audios";
    protected static final String VIEW_AUDIO_FORM = "audio-form";

    private IAudioService audioService;

    private IAudioPanelService audioPanelService;

    @Autowired
    public AudiosController(IAudioService audioService, IAudioPanelService audioPanelService) {
        this.audioService = audioService;
        this.audioPanelService = audioPanelService;
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

        model.addAttribute(AudioPanelController.MODEL_AUDIO_PANELS, audioPanelService.getAllAudioPanels());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_AUDIO_FORM;
    }

    @PostMapping(CTRL_AUDIOS + CTRL_NEW)
    public String newAudio(Audio audio){
        audioService.addNewAudio(audio);

        return "redirect:" + CTRL_RESOURCES + CTRL_AUDIOS;
    }
}
