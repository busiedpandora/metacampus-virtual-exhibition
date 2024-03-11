package metacampus2.controller;

import metacampus2.model.MenuItem;
import metacampus2.model.Metaverse;
import metacampus2.service.IMetaverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MetaverseController extends MainController {
    private static final String CTRL_METAVERSES = "/metaverses";
    private static final String VIEW_METAVERSES = "metaverses";
    private static final String VIEW_METAVERSE_FORM = "metaverse-form";

    private IMetaverseService metaverseService;


    @Autowired
    public MetaverseController(IMetaverseService metaverseService) {
        this.metaverseService = metaverseService;
    }

    @GetMapping(CTRL_METAVERSES)
    public String metaverses(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.METAVERSES);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        return VIEW_METAVERSES;
    }

    @GetMapping(CTRL_METAVERSES + CTRL_NEW)
    public String metaverseForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.METAVERSES);

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_METAVERSE_FORM;
    }

    @PostMapping(CTRL_METAVERSES + CTRL_NEW)
    public String metaverse(Metaverse metaverse) {
        if (metaverseService.getMetaverse(metaverse.getName()) == null) {
            metaverseService.addNewMetaverse(metaverse);

            return "redirect:" + CTRL_METAVERSES;
        }

        return "redirect:" + CTRL_METAVERSES + CTRL_NEW + "?error";
    }
}
