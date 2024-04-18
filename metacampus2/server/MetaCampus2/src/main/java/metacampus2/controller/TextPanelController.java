package metacampus2.controller;

import metacampus2.model.*;
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
import java.util.Objects;

@Controller
@RequestMapping(MainController.CTRL_SPACES)
public class TextPanelController extends MainController {
    protected static final String MODEL_TEXT_PANELS = "textPanels";
    protected static final String MODEL_TEXT_PANEL = "textPanel";
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
    public String newTextPanelForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT_PANEL);

        TextPanel textPanel = new TextPanel();
        textPanel.setCoordinates(new Coordinate());
        model.addAttribute(MODEL_TEXT_PANEL, textPanel);

        model.addAttribute(MODEL_TITLE, "New text panel:");
        model.addAttribute(MODEL_FORM_HREF, "/spaces/text-panels/new");

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_TEXT_PANEL_FORM;
    }

    @GetMapping(CTRL_TEXT_PANELS + "/{id}" + CTRL_EDIT)
    public String editTextPanelForm(Model model, @PathVariable("id") Long id,
                                    @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.SPACES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT_PANEL);

        TextPanel textPanel = textPanelService.getTextPanelById(id);
        model.addAttribute(MODEL_TEXT_PANEL, textPanel);

        model.addAttribute(MODEL_TITLE, "Edit text panel:");
        model.addAttribute(MODEL_FORM_HREF, "/spaces/text-panels/" + id + "/edit");

        model.addAttribute(MODEL_METAVERSES, List.of(textPanel.getMetaverse()));

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

        if(spaceService.getSpaceByCoordinatesAndMetaverse(coordinates.getX(), coordinates.getY(),
                coordinates.getZ(), textPanel.getMetaverse().getName()) != null) {
            return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS + CTRL_NEW
                    + "?error=a space with these coordinates already exists";
        }

        if(spaceService.getSpaceByNameAndMetaverse(textPanel.getName(),
                textPanel.getMetaverse().getName()) == null) {
            if(textPanelService.createDirectory(textPanel)) {
                textPanelService.addNewTextPanel(textPanel);

                return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS;
            }
        }

        return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS + CTRL_NEW
                + "?error=a text panel with this name already exists";
    }

    @PostMapping(CTRL_TEXT_PANELS + "/{id}" + CTRL_EDIT)
    public String editTextPanel(@PathVariable("id") Long id, TextPanel textPanel) {
        Coordinate coordinates = textPanel.getCoordinates();

        Space spaceByCoords = spaceService.getSpaceByCoordinatesAndMetaverse(coordinates.getX(), coordinates.getY(),
                coordinates.getZ(), textPanel.getMetaverse().getName());
        if(spaceByCoords != null && !Objects.equals(spaceByCoords.getId(), id)) {
            return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS + "/" + id + CTRL_EDIT
                    + "?error=a space with these coordinates already exists";
        }

        Space spaceByName = spaceService.getSpaceByNameAndMetaverse(textPanel.getName(),
                textPanel.getMetaverse().getName());
        if(spaceByName == null) {
            Space spaceById = spaceService.getSpaceById(id);

            if(textPanelService.renameDirectory(spaceById.getName(), textPanel)) {
                textPanelService.addNewTextPanel(textPanel);

                return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS;
            }
        }
        else if(Objects.equals(spaceByName.getId(), id)) {
            textPanelService.addNewTextPanel(textPanel);

            return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS;
        }

        return "redirect:" + CTRL_SPACES + CTRL_TEXT_PANELS + "/" + id + CTRL_EDIT
                + "?error=a text panel with this name already exists";
    }

    @GetMapping("/{metaverseUrlName}" + CTRL_TEXT_PANELS + "/{textPanelUrlName}"
            + CTRL_TEXTS + "/{textTitle}" + "/{textFileName}")
    @ResponseBody
    public String getText(@PathVariable("metaverseUrlName") String metaverseUrlName,
                          @PathVariable("textPanelUrlName") String textPanelUrlName,
                          @PathVariable("textTitle") String textTitle,
                          @PathVariable("textFileName") String textName) {

        return textPanelService.getTextFile(metaverseUrlName, textPanelUrlName, textTitle, textName);
    }
}
