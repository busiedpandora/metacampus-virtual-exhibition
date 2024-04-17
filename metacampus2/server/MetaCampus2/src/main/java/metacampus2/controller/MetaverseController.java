
package metacampus2.controller;

import metacampus2.model.MenuCategory;
import metacampus2.model.Metaverse;
import metacampus2.service.IMetaverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(MainController.CTRL_METAVERSES)
public class MetaverseController extends MainController {
    protected static final String CTRL_METAVERSES_LIST = "/metaversesList";
    private static final String VIEW_METAVERSES = "metaverses";
    private static final String VIEW_METAVERSE_FORM = "metaverse-form";

    private IMetaverseService metaverseService;


    @Autowired
    public MetaverseController(IMetaverseService metaverseService) {
        this.metaverseService = metaverseService;
    }

    @GetMapping
    public String metaverses(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.METAVERSES);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        return VIEW_METAVERSES;
    }

    @GetMapping( CTRL_NEW)
    public String metaverseForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.METAVERSES);

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_METAVERSE_FORM;
    }


    @PostMapping(CTRL_NEW)
    public String newMetaverse(Metaverse metaverse) {
        if(metaverse.getMinXDimension() > metaverse.getMaxXDimension()
            || metaverse.getMinYDimension() > metaverse.getMaxYDimension()
            || metaverse.getMinZDimension() > metaverse.getMaxZDimension()) {

            return "redirect:" + CTRL_METAVERSES + CTRL_NEW
                    + "?error=minimum dimension cannot be greater than maximum dimension";
        }

        if(metaverseService.getMetaverseByName(metaverse.getName()) == null) {
            if(metaverseService.createDirectory(metaverse)) {
                metaverseService.addNewMetaverse(metaverse);

                return "redirect:" + CTRL_METAVERSES;
            }
        }

        return "redirect:" + CTRL_METAVERSES + CTRL_NEW + "?error=a metaverse with this name already exists";
    }

    @GetMapping(CTRL_METAVERSES_LIST)
    public ResponseEntity<List<Metaverse>> metaversesList() {
        return new ResponseEntity<>(metaverseService.getAllMetaverses(), HttpStatus.OK);
    }

    @GetMapping("/{metaverseUrlName}")
    public ResponseEntity<Metaverse> metaverse(@PathVariable("metaverseUrlName")
                                                   String metaverseUrlName) {
        return new ResponseEntity<>(metaverseService.getMetaverseByUrlName(metaverseUrlName), HttpStatus.OK);
    }
}

