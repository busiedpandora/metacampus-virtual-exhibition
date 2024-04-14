package metacampus2.controller;

import metacampus2.model.Coordinate;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.model.TextPanel;
import metacampus2.service.IMetaverseService;
import metacampus2.service.ISpaceService;
import metacampus2.service.ITextPanelService;
import metacampus2.service.TextPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(MainController.CTRL_SPACES)
public class TextPanelController extends MainController {
    protected static final String MODEL_TEXT_PANELS = "textPanels";
    protected static final String VIEW_TEXT_PANELS = "text-panels";
    protected static final String VIEW_TEXT_PANEL_FORM = "text-panel-form";

    private ITextPanelService textPanelService;
    private IMetaverseService metaverseService;
    private ISpaceService spaceService;


    @Autowired
    public TextPanelController(TextPanelService textPanelService, IMetaverseService metaverseService,
                               ISpaceService spaceService) {
        this.textPanelService = textPanelService;
        this.metaverseService = metaverseService;
        this.spaceService = spaceService;
    }


    @GetMapping(CTRL_TEXT_PANELS)
    public String textPanels(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT_PANEL);

        model.addAttribute(MODEL_TEXT_PANELS, textPanelService.getAllTextPanels());

        return VIEW_TEXT_PANELS;
    }

    @GetMapping(CTRL_TEXT_PANELS + CTRL_NEW)
    public String textPanelForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT_PANEL);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_TEXT_PANEL_FORM;
    }

    @GetMapping("/{metaverseUrlName}" + CTRL_TEXT_PANELS)
    public ResponseEntity<List<TextPanel>> textPanelsFromMetaverse(@PathVariable("metaverseUrlName")
                                                                       String metaverseUrlName) {
        return new ResponseEntity<>(
                textPanelService.getAllTextPanelsFromMetaverseByUrlName(metaverseUrlName),
                HttpStatus.OK);
    }

    @PostMapping(CTRL_TEXT_PANELS + CTRL_NEW)
    public String newTextPanel(TextPanel textPanel) {
        Coordinate coordinates = textPanel.getCoordinates();

        if(spaceService.getSpaceByNameAndMetaverse(textPanel.getName(), textPanel.getMetaverse().getName()) == null &&
                spaceService.getSpaceByCoordinatesAndMetaverse(coordinates.getX(), coordinates.getY(),
                coordinates.getZ(), textPanel.getMetaverse().getName()) == null) {

            if(textPanelService.createDirectory(textPanel)) {
                textPanelService.addNewTextPanel(textPanel);

                return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS;
            }
        }

        return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS + CTRL_NEW + "?error";
    }

    @GetMapping("/{metaverseUrlName}" + CTRL_TEXT_PANELS + "/{textPanelUrlName}" + CTRL_TEXTS + "/{textName}")
    @ResponseBody
    public String getText(@PathVariable("metaverseUrlName") String metaverseUrlName,
                          @PathVariable("textPanelUrlName") String textPanelUrlName,
                          @PathVariable("textName") String textName) {

        return textPanelService.getTextFile(metaverseUrlName, textPanelUrlName, textName);
    }
}
