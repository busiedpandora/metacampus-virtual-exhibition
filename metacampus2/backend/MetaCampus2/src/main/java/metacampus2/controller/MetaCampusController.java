package metacampus2.controller;

import metacampus2.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MetaCampusController {
    public static final String CTRL_NEW = "/new";
    public static final String MODEL_MENU_ITEM = "menuItem";
    private static final String CTRL_HOME = "/home";
    public static final String CTRL_CLASSROOMS = "/classrooms";
    public static final String CTRL_EVENTS = "/events";
    public static final String CTRL_LECTURES = "/lectures";
    public static final String CTRL_OFFICES = "/offices";
    public static final String CTRL_PEOPLE = "/people";
    public static final String CTRL_METAVERSES = "/metaverses";


    @GetMapping({"/", CTRL_HOME})
    public String homepage(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.HOME);

        return "homepage";
    }

    @GetMapping(CTRL_CLASSROOMS)
    public String classrooms(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.CLASSROOMS);

        return "classrooms";
    }

    @GetMapping(CTRL_CLASSROOMS + CTRL_NEW)
    public String classroomForm(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.CLASSROOMS);

        return "classroom-form";
    }

    @PostMapping(CTRL_CLASSROOMS + CTRL_NEW)
    public String classroom(Classroom classroom) {

        return "redirect:" + CTRL_CLASSROOMS;
    }

    @GetMapping(CTRL_EVENTS)
    public String events(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.EVENTS);

        return "events";
    }

    @GetMapping(CTRL_EVENTS + CTRL_NEW)
    public String eventForm(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.EVENTS);

        return "event-form";
    }

    @PostMapping(CTRL_EVENTS + CTRL_NEW)
    public String event(Event event) {

        return "redirect:" + CTRL_EVENTS;
    }

    @GetMapping(CTRL_LECTURES)
    public String lectures(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.LECTURES);

        return "lectures";
    }

    @GetMapping(CTRL_LECTURES + CTRL_NEW)
    public String lectureForm(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.LECTURES);

        return "lecture-form";
    }

    @PostMapping(CTRL_LECTURES + CTRL_NEW)
    public String lecture(Lecture lecture) {

        return "redirect:" + CTRL_LECTURES;
    }

    @GetMapping(CTRL_METAVERSES)
    public String metaverses(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.METAVERSES);

        return "metaverses";
    }

    @GetMapping(CTRL_METAVERSES + CTRL_NEW)
    public String metaverseForm(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.METAVERSES);

        return "metaverse-form";
    }

    @PostMapping(CTRL_METAVERSES + CTRL_NEW)
    public String metaverse(Metaverse metaverse) {

        return "redirect:" + CTRL_METAVERSES;
    }

    @GetMapping(CTRL_OFFICES)
    public String offices(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.OFFICES);

        return "offices";
    }

    @GetMapping(CTRL_OFFICES + CTRL_NEW)
    public String officeForm(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.OFFICES);

        return "office-form";
    }

    @PostMapping(CTRL_OFFICES + CTRL_NEW)
    public String office(Office office) {

        return "redirect:" + CTRL_OFFICES;
    }

    @GetMapping(CTRL_PEOPLE)
    public String people(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.PEOPLE);

        return "people";
    }

    @GetMapping(CTRL_PEOPLE + CTRL_NEW)
    public String personForm(Model model) {

        model.addAttribute(MODEL_MENU_ITEM, MenuItem.PEOPLE);

        return "person-form";
    }

    @PostMapping(CTRL_PEOPLE + CTRL_NEW)
    public String person(Person person) {

        return "redirect:" + CTRL_PEOPLE;
    }
}
