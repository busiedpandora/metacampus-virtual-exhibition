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

    @GetMapping("/{metaverseName}" + CTRL_DISPLAY_PANELS)
    public ResponseEntity<List<DisplayPanel>> displayPanelsFromMetaverse(@PathVariable("metaverseName") String metaverseName) {
        return new ResponseEntity<>(displayPanelService.getAllDisplayPanelsFromMetaverse(metaverseName), HttpStatus.OK);
    }

    @PostMapping(CTRL_DISPLAY_PANELS + CTRL_NEW)
    public String newDisplayPanel(DisplayPanel displayPanel) {
        Coordinate coordinates = displayPanel.getCoordinates();

        if(spaceService.getSpaceByCoordinatesAndMetaverse(coordinates.getX(), coordinates.getY(),
                coordinates.getZ(), displayPanel.getMetaverse().getName()) == null) {
            displayPanelService.addNewDisplayPanel(displayPanel);

            return "redirect:" + CTRL_SPACES + CTRL_DISPLAY_PANELS;
        }

        return "redirect:" + CTRL_SPACES + CTRL_DISPLAY_PANELS + CTRL_NEW + "?error";
    }
}
