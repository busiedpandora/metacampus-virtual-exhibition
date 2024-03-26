package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.ITextPanelService;
import metacampus2.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class TextController extends MainController {
    protected static final String MODEL_TEXTS = "texts";
    protected static final String VIEW_TEXTS = "texts";
    protected static final String VIEW_TEXT_FORM = "text-form";

    private ITextService textService;
    private ITextPanelService textPanelService;


    @Autowired
    public TextController(ITextService textService, ITextPanelService textPanelService) {
        this.textService = textService;
        this.textPanelService = textPanelService;
    }

    @GetMapping(CTRL_TEXTS)
    public String texts(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT);

        model.addAttribute(MODEL_TEXTS, textService.getAllTexts());

        return VIEW_TEXTS;
    }

    @GetMapping(CTRL_TEXTS + CTRL_NEW)
    public String textForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT);

        model.addAttribute(TextPanelController.MODEL_TEXT_PANELS, textPanelService.getAllTextPanels());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_TEXT_FORM;
    }

    @PostMapping(CTRL_TEXTS + CTRL_NEW)
    public String newText(Text text) {
        textService.addNewText(text);

        return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS;
    }
}
