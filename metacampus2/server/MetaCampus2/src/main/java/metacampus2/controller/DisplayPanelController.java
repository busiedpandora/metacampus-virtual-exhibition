package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.IDisplayPanelService;
import metacampus2.service.IMetaverseService;
import metacampus2.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping(MainController.CTRL_SPACES)
public class DisplayPanelController extends MainController {
    protected static final String MODEL_DISPLAY_PANELS = "displayPanels";
    protected static final String MODEL_DISPLAY_PANEL_TYPES = "displayPanelTypes";
    protected static final String VIEW_DISPLAY_PANELS = "display-panels";
    protected static final String VIEW_DISPLAY_PANEL_FORM = "display-panel-form";

    private IDisplayPanelService displayPanelService;
    private IMetaverseService metaverseService;
    private ISpaceService spaceService;


    @Autowired
    public DisplayPanelController(IDisplayPanelService displayPanelService, IMetaverseService metaverseService,
                                  ISpaceService spaceService) {
        this.displayPanelService = displayPanelService;
        this.metaverseService = metaverseService;
        this.spaceService = spaceService;
    }

    @GetMapping(CTRL_DISPLAY_PANELS)
    public String displayPanels(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.DISPLAY_PANEL);

        model.addAttribute(MODEL_DISPLAY_PANELS, displayPanelService.getAllDisplayPanels());

        return VIEW_DISPLAY_PANELS;
    }

    @GetMapping(CTRL_DISPLAY_PANELS + CTRL_NEW)
    public String displayPanelForm(Model model,
                                   @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.DISPLAY_PANEL);

        model.addAttribute(MODEL_DISPLAY_PANEL_TYPES, DisplayPanelType.getAllDisplayPanelTypes());
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_DISPLAY_PANEL_FORM;
    }

    @GetMapping("/{metaverseUrlName}" + CTRL_DISPLAY_PANELS)
    public ResponseEntity<List<DisplayPanel>> displayPanelsFromMetaverse(
            @PathVariable("metaverseUrlName") String metaverseUrlName) {
        return new ResponseEntity<>(
                displayPanelService.getAllDisplayPanelsFromMetaverseByUrlName(metaverseUrlName),
                HttpStatus.OK);
    }

    @PostMapping(CTRL_DISPLAY_PANELS + CTRL_NEW)
    public String newDisplayPanel(DisplayPanel displayPanel) {
        Coordinate coordinates = displayPanel.getCoordinates();

        if (spaceService.getSpaceByNameAndMetaverse(displayPanel.getName(), displayPanel.getMetaverse().getName()) == null
                && spaceService.getSpaceByCoordinatesAndMetaverse(coordinates.getX(), coordinates.getY(),
                coordinates.getZ(), displayPanel.getMetaverse().getName()) == null) {

            displayPanelService.addNewDisplayPanel(displayPanel);

            File displayPanelDirectory = new File(METAVERSES_PATH
                    + displayPanel.getMetaverse().getUrlName() + SEPARATOR + DISPLAY_PANELS_PATH + displayPanel.getUrlName());

            if (!displayPanelDirectory.exists()) {
                displayPanelDirectory.mkdirs();
            }

            return "redirect:" + CTRL_SPACES + CTRL_DISPLAY_PANELS;
        }

        return "redirect:" + CTRL_SPACES + CTRL_DISPLAY_PANELS + CTRL_NEW + "?error";
    }

    @GetMapping("/{metaverseUrlName}" + CTRL_DISPLAY_PANELS + "/{displayPanelUrlName}" + CTRL_IMAGES + "/{imageName}")
    @ResponseBody
    public String getImage(@PathVariable("metaverseUrlName") String metaverseUrlName,
                           @PathVariable("displayPanelUrlName") String displayPanelUrlName,
                           @PathVariable("imageName") String imageName) {
        try {
            String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
            File imagesDirectory = new File(METAVERSES_PATH + metaverseUrlName +
                    SEPARATOR + DISPLAY_PANELS_PATH + displayPanelUrlName + SEPARATOR +
                    IMAGES_PATH + imageNameWithoutExtension);
            if (!imagesDirectory.exists()) {
                return null;
            }

            Path imagePath = Path.of(imagesDirectory.getPath() + SEPARATOR + imageName);
            if (!Files.exists(imagePath)) {
                return null;
            }

            return Base64.getEncoder().encodeToString(Files.readAllBytes(imagePath));

        } catch (IOException e) {
            return null;
        }
    }

    @GetMapping("/{metaverseUrlName}" + CTRL_DISPLAY_PANELS + "/{displayPanelUrlName}"
            + CTRL_IMAGES + "/{imageName}" + CTRL_AUDIOS + "/{audioName}")
    public ResponseEntity<byte[]> getAudio(@PathVariable("metaverseUrlName") String metaverseUrlName,
                                           @PathVariable("displayPanelUrlName") String displayPanelUrlName,
                                           @PathVariable("imageName") String imageName,
                                           @PathVariable("audioName") String audioName) {
        try {
            String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
            File audioDirectory = new File(METAVERSES_PATH + metaverseUrlName +
                    SEPARATOR + DISPLAY_PANELS_PATH + displayPanelUrlName + SEPARATOR + IMAGES_PATH +
                    imageNameWithoutExtension + SEPARATOR + AUDIO_PATH);
            if (!audioDirectory.exists()) {
                return ResponseEntity.notFound().build();
            }

            Path audioPath = Path.of(audioDirectory.getPath() + SEPARATOR + audioName);

            if (!Files.exists(audioPath)) {
                return ResponseEntity.notFound().build();
            }

            byte[] audioData = Files.readAllBytes(audioPath);
            return ResponseEntity.ok()
                    .header("Content-Type", "audio/wav")
                    .body(audioData);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
