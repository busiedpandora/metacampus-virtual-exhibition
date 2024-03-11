package metacampus2.controller;

import metacampus2.model.MenuItem;
import metacampus2.model.Metaverse;
import metacampus2.model.Person;
import metacampus2.model.Role;
import metacampus2.service.IMetaverseService;
import metacampus2.service.IOfficeService;
import metacampus2.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class PersonController extends MainController {
    private static final String CTRL_PEOPLE = "/people";
    private static final String MODEL_ROLES = "roles";
    private static final String VIEW_PEOPLE = "people";
    private static final String VIEW_PERSON_FORM = "person-form";

    private IPersonService personService;
    private IMetaverseService metaverseService;
    private IOfficeService officeService;


    @Autowired
    public PersonController(IPersonService personService, IMetaverseService metaverseService, IOfficeService officeService) {
        this.personService = personService;
        this.metaverseService = metaverseService;
        this.officeService = officeService;
    }

    @GetMapping(CTRL_PEOPLE)
    public String people(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.PEOPLE);

        model.addAttribute(MODEL_PEOPLE, personService.getAllPeople());

        return VIEW_PEOPLE;
    }

    @GetMapping(CTRL_PEOPLE + CTRL_NEW)
    public String personForm(Model model,
                             @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.PEOPLE);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());
        model.addAttribute(MODEL_ROLES, Role.values());
        model.addAttribute(MODEL_OFFICES, officeService.getAllOffices());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_PERSON_FORM;
    }

    @PostMapping(CTRL_PEOPLE + CTRL_NEW)
    public String person(Person person) {
        Metaverse metaverse = person.getMetaverse();
        if (person.getOffice() != null &&
                !Objects.equals(metaverse, person.getOffice().getMetaverse())) {
            return "redirect:" + CTRL_PEOPLE + CTRL_NEW + "?error";
        }

        if (personService.getPersonFromMetaverse(person.getFirstName(), person.getLastName(),
                person.getCellphone(), person.getMetaverse().getName()) == null) {
            personService.addNewPerson(person);

            return "redirect:" + CTRL_PEOPLE;
        }

        return "redirect:" + CTRL_PEOPLE + CTRL_NEW + "?error";
    }
}
