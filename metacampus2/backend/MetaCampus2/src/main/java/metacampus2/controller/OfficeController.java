/*
package metacampus2.controller;

import metacampus2.model.MenuItem;
import metacampus2.model.Office;
import metacampus2.service.IMetaverseService;
import metacampus2.service.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OfficeController extends MainController {
    private static final String CTRL_OFFICES = "/offices";
    private static final String VIEW_OFFICES = "offices";
    private static final String VIEW_OFFICE_FORM = "office-form";

    private IOfficeService officeService;
    private IMetaverseService metaverseService;


    @Autowired
    public OfficeController(IOfficeService officeService, IMetaverseService metaverseService) {
        this.officeService = officeService;
        this.metaverseService = metaverseService;
    }

    @GetMapping(CTRL_OFFICES)
    public String offices(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.OFFICES);

        model.addAttribute(MODEL_OFFICES, officeService.getAllOffices());

        return VIEW_OFFICES;
    }

    @GetMapping("/{metaverseName}" + CTRL_OFFICES)
    public ResponseEntity<List<Office>> offices(@PathVariable("metaverseName") String metaverseName) {
        return new ResponseEntity<>(officeService.getAllOfficesFromMetaverse(metaverseName), HttpStatus.OK);
    }

    @GetMapping(CTRL_OFFICES + CTRL_NEW)
    public String officeForm(Model model,
                             @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.OFFICES);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_OFFICE_FORM;
    }

    @PostMapping(CTRL_OFFICES + CTRL_NEW)
    public String office(Office office) {
        if (officeService.getOfficeFromMetaverse(office.getNumber(),
                office.getMetaverse().getName()) == null) {
            officeService.addNewOffice(office);

            return "redirect:" + CTRL_OFFICES;
        }

        return "redirect:" + CTRL_OFFICES + CTRL_NEW + "?error";
    }
}
*/
