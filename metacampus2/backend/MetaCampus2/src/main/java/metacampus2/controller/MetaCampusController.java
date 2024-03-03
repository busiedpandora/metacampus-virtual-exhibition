package metacampus2.controller;

import metacampus2.model.*;
import metacampus2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class MetaCampusController {
    public static final String CTRL_NEW = "/new";
    private static final String CTRL_HOME = "/home";
    public static final String CTRL_CLASSROOMS = "/classrooms";
    public static final String CTRL_EVENTS = "/events";
    public static final String CTRL_LECTURES = "/lectures";
    public static final String CTRL_OFFICES = "/offices";
    public static final String CTRL_PEOPLE = "/people";
    public static final String CTRL_METAVERSES = "/metaverses";

    public static final String MODEL_PROJECT_NAME = "projectName";
    public static final String MODEL_MENU_ITEM = "menuItem";
    public static final String MODEL_ERROR = "error";
    public static final String MODEL_CLASSROOMS = "classrooms";
    public static final String MODEL_EVENTS = "events";
    public static final String MODEL_OFFICES = "offices";
    public static final String MODEL_LECTURES = "lectures";
    public static final String MODEL_PEOPLE = "people";
    public static final String MODEL_LECTURERS = "lecturers";
    public static final String MODEL_METAVERSES = "metaverses";
    public static final String MODEL_ROLES = "roles";


    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private MetaverseService metaverseService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private EventService eventService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private PersonService personService;

    @Value("${project.name}")
    private String projectName;

    @ModelAttribute
    public void addDefaultAttributes(Model model) {
        model.addAttribute(MODEL_PROJECT_NAME, projectName);
    }

    @GetMapping({"/", CTRL_HOME})
    public String homepage(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.HOME);

        return "homepage";
    }

    @GetMapping(CTRL_CLASSROOMS)
    public String classrooms(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.CLASSROOMS);
        model.addAttribute(MODEL_CLASSROOMS, classroomService.getAllClassrooms());

        return "classrooms";
    }

    @GetMapping(CTRL_CLASSROOMS + CTRL_NEW)
    public String classroomForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.CLASSROOMS);
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return "classroom-form";
    }

    @PostMapping(CTRL_CLASSROOMS + CTRL_NEW)
    public String classroom(Classroom classroom) {
        if(classroomService.getClassroomFromMetaverse(classroom.getNumber(),
                classroom.getMetaverse().getName()) == null) {
            classroomService.addNewClassroom(classroom);

            return "redirect:" + CTRL_CLASSROOMS;
        }

        return "redirect:" + CTRL_CLASSROOMS + CTRL_NEW + "?error";
    }

    @GetMapping(CTRL_EVENTS)
    public String events(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.EVENTS);
        model.addAttribute(MODEL_EVENTS, eventService.getAllEvents());

        return "events";
    }

    @GetMapping(CTRL_EVENTS + CTRL_NEW)
    public String eventForm(Model model,
                            @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.EVENTS);
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return "event-form";
    }

    @PostMapping(CTRL_EVENTS + CTRL_NEW)
    public String event(Event event) {
        if(eventService.getEventFromMetaverse(event.getName(),
                event.getDateTime(), event.getMetaverse().getName()) == null) {
            eventService.addNewEvent(event);

            return "redirect:" + CTRL_EVENTS;
        }

        return "redirect:" + CTRL_EVENTS + CTRL_NEW + "?error";
    }

    @GetMapping(CTRL_LECTURES)
    public String lectures(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.LECTURES);

        model.addAttribute(MODEL_LECTURES, lectureService.getAllLectures());

        return "lectures";
    }

    @GetMapping(CTRL_LECTURES + CTRL_NEW)
    public String lectureForm(Model model,
                              @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.LECTURES);
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_CLASSROOMS, classroomService.getAllClassrooms());
        model.addAttribute(MODEL_LECTURERS, personService.getAllLecturers());

        model.addAttribute(MODEL_ERROR, error);

        return "lecture-form";
    }

    @PostMapping(CTRL_LECTURES + CTRL_NEW)
    public String lecture(Lecture lecture) {
        Metaverse metaverse = lecture.getClassroom().getMetaverse();
        if(!Objects.equals(metaverse.getId(),
                lecture.getLecturer().getMetaverse().getId())) {
            return "redirect:" + CTRL_LECTURES + CTRL_NEW + "?error";
        }

        lecture.setMetaverse(metaverse);
        if(lectureService.getLectureFromMetaverse(lecture.getName(), lecture.getDateTime(),
                lecture.getMetaverse().getName()) == null) {
            lectureService.addNewLecture(lecture);

            return "redirect:" + CTRL_LECTURES;
        }

        return "redirect:" + CTRL_LECTURES + CTRL_NEW + "?error";
    }

    @GetMapping(CTRL_METAVERSES)
    public String metaverses(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.METAVERSES);
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        return "metaverses";
    }

    @GetMapping(CTRL_METAVERSES + CTRL_NEW)
    public String metaverseForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.METAVERSES);

        model.addAttribute(MODEL_ERROR, error);

        return "metaverse-form";
    }

    @PostMapping(CTRL_METAVERSES + CTRL_NEW)
    public String metaverse(Metaverse metaverse) {
        if(metaverseService.getMetaverse(metaverse.getName()) == null) {
            metaverseService.addNewMetaverse(metaverse);

            return "redirect:" + CTRL_METAVERSES;
        }

        return "redirect:" + CTRL_METAVERSES + CTRL_NEW + "?error";
    }

    @GetMapping(CTRL_OFFICES)
    public String offices(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.OFFICES);
        model.addAttribute(MODEL_OFFICES, officeService.getAllOffices());

        return "offices";
    }

    @GetMapping(CTRL_OFFICES + CTRL_NEW)
    public String officeForm(Model model,
                             @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.OFFICES);
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return "office-form";
    }

    @PostMapping(CTRL_OFFICES + CTRL_NEW)
    public String office(Office office) {
        if(officeService.getOfficeFromMetaverse(office.getNumber(),
                office.getMetaverse().getName()) == null) {
            officeService.addNewOffice(office);

            return "redirect:" + CTRL_OFFICES;
        }

        return "redirect:" + CTRL_OFFICES + CTRL_NEW + "?error";
    }

    @GetMapping(CTRL_PEOPLE)
    public String people(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.PEOPLE);

        model.addAttribute(MODEL_PEOPLE, personService.getAllPeople());

        return "people";
    }

    @GetMapping(CTRL_PEOPLE + CTRL_NEW)
    public String personForm(Model model,
                             @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.PEOPLE);
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ROLES, Role.values());
        model.addAttribute(MODEL_OFFICES, officeService.getAllOffices());

        model.addAttribute(MODEL_ERROR, error);

        return "person-form";
    }

    @PostMapping(CTRL_PEOPLE + CTRL_NEW)
    public String person(Person person) {
        Metaverse metaverse = person.getMetaverse();
        if(person.getOffice() != null &&
                !Objects.equals(metaverse, person.getOffice().getMetaverse())) {
            return "redirect:" + CTRL_PEOPLE + CTRL_NEW + "?error";
        }

        if(personService.getPersonFromMetaverse(person.getFirstName(), person.getLastName(),
                person.getCellphone(), person.getMetaverse().getName()) == null) {
            personService.addNewPerson(person);

            return "redirect:" + CTRL_PEOPLE;
        }

        return "redirect:" + CTRL_PEOPLE + CTRL_NEW + "?error";
    }
}
