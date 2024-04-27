
package metacampus2.controller;

import metacampus2.model.MenuCategory;
import metacampus2.model.Metaverse;
import metacampus2.model.UserRole;
import metacampus2.service.IMetaverseService;
import metacampus2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(MainController.CTRL_METAVERSES)
public class MetaverseController extends MainController {
    protected static final String CTRL_METAVERSES_LIST = "/metaversesList";
    protected static final String MODEL_METAVERSE = "metaverse";
    protected static final String VIEW_METAVERSES = "metaverses";
    protected static final String VIEW_METAVERSE_FORM = "metaverse-form";

    private IMetaverseService metaverseService;
    private IUserService userService;


    @Autowired
    public MetaverseController(IMetaverseService metaverseService,
                               IUserService userService) {
        this.metaverseService = metaverseService;
        this.userService = userService;
    }

    @GetMapping
    public String metaverses(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.METAVERSES);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        return VIEW_METAVERSES;
    }

    @GetMapping( CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
    public String newMetaverseForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.METAVERSES);

        Metaverse metaverse = new Metaverse();
        model.addAttribute(MODEL_METAVERSE, metaverse);

        model.addAttribute(MODEL_TITLE, "New metaverse:");
        model.addAttribute(MODEL_FORM_HREF, CTRL_METAVERSES + CTRL_NEW);

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_METAVERSE_FORM;
    }

    @GetMapping("/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('CREATOR')")
    public String editMetaverseForm(Model model, @PathVariable("id") Long id,
                                    @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.METAVERSES);

        Metaverse metaverse = metaverseService.getMetaverseById(id);
        model.addAttribute(MODEL_METAVERSE, metaverse);

        model.addAttribute(MODEL_TITLE, "Edit metaverse:");
        model.addAttribute(MODEL_FORM_HREF, CTRL_METAVERSES + "/" + id + CTRL_EDIT);

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_METAVERSE_FORM;
    }

    @PostMapping(CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
    public String newMetaverse(Metaverse metaverse) {
        if(metaverse.getMinXDimension() > metaverse.getMaxXDimension()
            || metaverse.getMinYDimension() > metaverse.getMaxYDimension()
            || metaverse.getMinZDimension() > metaverse.getMaxZDimension()) {

            return "redirect:" + CTRL_METAVERSES + CTRL_NEW
                    + "?error=minimum dimension cannot be greater than maximum dimension";
        }

        if(metaverseService.getMetaverseByName(metaverse.getName()) == null) {
            if(metaverseService.createDirectory(metaverse)) {
                metaverse.setCreator(userService.getUserLogged());

                metaverseService.addNewMetaverse(metaverse);

                return "redirect:" + CTRL_METAVERSES;
            }
        }

        return "redirect:" + CTRL_METAVERSES + CTRL_NEW + "?error=a metaverse with this name already exists";
    }

    @PostMapping("/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('ADMIN') || hasRole('CREATOR') && #metaverse.creator.username == authentication.name")
    public String editMetaverse(@PathVariable("id") Long id, @P("metaverse") Metaverse metaverse) {
        if(metaverse.getMinXDimension() > metaverse.getMaxXDimension()
                || metaverse.getMinYDimension() > metaverse.getMaxYDimension()
                || metaverse.getMinZDimension() > metaverse.getMaxZDimension()) {

            return "redirect:" + CTRL_METAVERSES + "/" + id + CTRL_EDIT
                    + "?error=minimum dimension cannot be greater than maximum dimension";
        }

        Metaverse metaverseByName = metaverseService.getMetaverseByName(metaverse.getName());

        if(metaverseByName == null) {
            Metaverse metaverseById = metaverseService.getMetaverseById(id);
            if(metaverseService.renameDirectory(metaverseById.getName(), metaverse)) {
                metaverseService.addNewMetaverse(metaverse);

                return "redirect:" + CTRL_METAVERSES;
            }
        }
        else if(Objects.equals(metaverseByName.getId(), id)) {
            metaverseService.addNewMetaverse(metaverse);

            return "redirect:" + CTRL_METAVERSES;
        }

        return "redirect:" + CTRL_METAVERSES + "/" + id + CTRL_EDIT + "?error=a metaverse with this name already exists";
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

    @GetMapping("/{id}" + CTRL_DELETE)
    @PreAuthorize("hasRole('CREATOR')")
    public String deleteMetaverse(@PathVariable("id") Long id) {
        Metaverse metaverse = metaverseService.getMetaverseById(id);

        if(metaverse != null) {
            if(userService.getUserLogged().getRole() == UserRole.ROLE_ADMIN
                    || userService.getUserLogged().equals(metaverse.getCreator())) {
                metaverseService.deleteDirectory(metaverse);
                metaverseService.deleteMetaverse(metaverse);
            }
        }

        return "redirect:" + CTRL_METAVERSES;
    }
}

