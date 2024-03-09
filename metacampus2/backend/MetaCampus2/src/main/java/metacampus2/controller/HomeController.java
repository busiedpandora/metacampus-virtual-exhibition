package metacampus2.controller;

import metacampus2.model.MenuItem;
import metacampus2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends MainController {
    private static final String CTRL_HOME = "/home";
    private static final String VIEW_HOME = "homepage";

    private IClassroomService classroomService;
    private IMetaverseService metaverseService;
    private IOfficeService officeService;
    private IEventService eventService;
    private ILectureService lectureService;
    private IPersonService personService;


    public HomeController(IClassroomService classroomService, IMetaverseService metaverseService,
                          IOfficeService officeService, IEventService eventService,
                          ILectureService lectureService, IPersonService personService) {
        this.classroomService = classroomService;
        this.metaverseService = metaverseService;
        this.officeService = officeService;
        this.eventService = eventService;
        this.lectureService = lectureService;
        this.personService = personService;
    }

    @GetMapping({"/", CTRL_HOME})
    public String homepage(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.HOME);

        model.addAttribute(MODEL_CLASSROOMS, classroomService.getAllClassrooms());
        model.addAttribute(MODEL_EVENTS, eventService.getAllEvents());
        model.addAttribute(MODEL_LECTURES, lectureService.getAllLectures());
        model.addAttribute(MODEL_OFFICES, officeService.getAllOffices());
        model.addAttribute(MODEL_PEOPLE, personService.getAllPeople());
        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        return VIEW_HOME;
    }
}
