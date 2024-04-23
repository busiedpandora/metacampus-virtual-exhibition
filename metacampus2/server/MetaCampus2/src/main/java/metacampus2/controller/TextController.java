package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.ITextPanelService;
import metacampus2.service.ITextService;
import metacampus2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class TextController extends MainController {
    protected static final String MODEL_TEXTS = "texts";
    protected static final String MODEL_TEXT = "text";
    protected static final String VIEW_TEXTS = "texts";
    protected static final String VIEW_TEXT_FORM = "text-form";

    private ITextService textService;
    private ITextPanelService textPanelService;
    private IUserService userService;


    @Autowired
    public TextController(ITextService textService, ITextPanelService textPanelService, IUserService userService) {
        this.textService = textService;
        this.textPanelService = textPanelService;
        this.userService = userService;
    }

    @GetMapping(CTRL_TEXTS)
    public String texts(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT);

        model.addAttribute(MODEL_TEXTS, textService.getAllTexts());

        return VIEW_TEXTS;
    }

    @GetMapping(CTRL_TEXTS + CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
    public String newTextForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT);

        Text text = new Text();
        model.addAttribute(MODEL_TEXT, text);
        model.addAttribute(MODEL_ON_EDIT, false);

        model.addAttribute(MODEL_TITLE, "New text:");
        model.addAttribute(MODEL_FORM_HREF, "/resources/texts/new");

        model.addAttribute(TextPanelController.MODEL_TEXT_PANELS, textPanelService.getAllTextPanels());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_TEXT_FORM;
    }

    @GetMapping(CTRL_TEXTS + "/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('CREATOR')")
    public String editTextForm(Model model, @PathVariable("id") Long id,
                               @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.TEXT);

        Text text = textService.getTextById(id);
        model.addAttribute(MODEL_TEXT, text);
        model.addAttribute(MODEL_ON_EDIT, true);

        model.addAttribute(MODEL_TITLE, "Edit text:");
        model.addAttribute(MODEL_FORM_HREF, "/resources/texts/" + id + "/edit");

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_TEXT_FORM;
    }

    @PostMapping(CTRL_TEXTS + CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
    public String newText(Text text, @RequestParam(value = "textFile") MultipartFile textFile) {
        if(textFile != null && !textFile.isEmpty()) {
            if(textService.getTextByTitle(text.getTitle()) != null) {
                return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS + CTRL_NEW
                        + "?error=a text with this title already exists";
            }

            String textFileName = textFile.getOriginalFilename();
            for(TextPanel textPanel : text.getTextPanels()) {
                if(!textService.createFile(text, textFile, textPanel)) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS + CTRL_NEW
                            + "?error=error while copying text file into resources folder";
                }
            }

            text.setCreator(userService.getUserLogged());
            text.setFileName(textFileName);
            textService.addNewText(text);

            return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS + CTRL_NEW + "?error=text file is null or empty";
    }

    @PostMapping(CTRL_TEXTS + "/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('ADMIN') || hasRole('CREATOR') && #text.creator.username == authentication.name")
    public String editText(@PathVariable("id") Long id, @P("text") Text text) {
        Text textByTitle = textService.getTextByTitle(text.getTitle());
        if(textByTitle == null || Objects.equals(textByTitle.getId(), id)) {
            Text textById = textService.getTextById(id);
            for(TextPanel textPanel : text.getTextPanels()) {
                if(!textService.renameFile(textById.getTitle(), text, textPanel)) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS + "/" + id + CTRL_EDIT
                            + "?error=error occured while renaming the text file";
                }
            }

            textService.editText(text);

            return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_TEXTS + "/" + id + CTRL_EDIT
                + "?error=a text with this title already exists";
    }
}
