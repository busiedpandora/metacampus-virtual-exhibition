package metacampus2.controller;

import metacampus2.model.AudioPanel;
import metacampus2.model.Coordinate;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.service.IAudioPanelService;
import metacampus2.service.IMetaverseService;
import metacampus2.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(MainController.CTRL_SPACES)
public class AudioPanelController extends MainController {

    protected static final String MODEL_AUDIO_PANELS = "audioPanels";
    protected static final String VIEW_AUDIO_PANELS = "audio-panels";
    protected static final String VIEW_AUDIO_PANEL_FORM = "audio-panel-form";


    private IAudioPanelService audioPanelService;
    private IMetaverseService metaverseService;
    private ISpaceService spaceService;

    @Autowired
    public AudioPanelController(IAudioPanelService audioPanelService, IMetaverseService metaverseService, ISpaceService spaceService) {
        this.audioPanelService = audioPanelService;
        this.metaverseService = metaverseService;
        this.spaceService = spaceService;
    }

    @GetMapping(CTRL_AUDIO_PANELS)
    public String audioPanels(Model model){
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.AUDIO_PANEL);

        model.addAttribute(MODEL_AUDIO_PANELS, audioPanelService.getAllAudioPanels());

        return VIEW_AUDIO_PANELS;
    }

    @GetMapping(CTRL_AUDIO_PANELS + CTRL_NEW)
    public String imagePanelForm(Model model, @RequestParam(value = "error", required = false) String error){
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.AUDIO_PANEL);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_AUDIO_PANEL_FORM;
    }

    @GetMapping("/{metaverseName}" + CTRL_AUDIO_PANELS)
    public ResponseEntity<List<AudioPanel>> audioPanelsFromMetaverse(@PathVariable("metaverseName") String metaverseName){
        return new ResponseEntity<>(audioPanelService.getAllAudioPanelsFromMetaverse(metaverseName), HttpStatus.OK);
    }

    @PostMapping(CTRL_AUDIO_PANELS + CTRL_NEW)
    public String newAudioPanel(AudioPanel audioPanel){
        Coordinate coordinates = audioPanel.getCoordinates();

        if(spaceService.getSpaceByCoordinatesAndMetaverse(coordinates.getX(), coordinates.getY(), coordinates.getZ(), audioPanel.getMetaverse().getName()) == null){

            audioPanelService.addNewAudioPane(audioPanel);

            return "redirect:" + CTRL_SPACES + CTRL_AUDIO_PANELS;
        }

        return "redirect:" + CTRL_SPACES + CTRL_AUDIO_PANELS + CTRL_NEW + "?error";
    }
}
