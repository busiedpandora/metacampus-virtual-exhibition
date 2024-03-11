package metacampus2.controller;

import metacampus2.model.Lecture;
import metacampus2.model.MenuItem;
import metacampus2.model.Metaverse;
import metacampus2.service.IClassroomService;
import metacampus2.service.ILectureService;
import metacampus2.service.IMetaverseService;
import metacampus2.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class LectureController extends MainController {
    private static final String CTRL_LECTURES = "/lectures";
    private static final String MODEL_LECTURERS = "lecturers";
    private static final String VIEW_LECTURES = "lectures";
    private static final String VIEW_LECTURE_FORM = "lecture-form";

    private ILectureService lectureService;
    private IMetaverseService metaverseService;
    private IClassroomService classroomService;
    private IPersonService personService;


    @Autowired
    public LectureController(ILectureService lectureService, IMetaverseService metaverseService,
                             IClassroomService classroomService, IPersonService personService) {
        this.lectureService = lectureService;
        this.metaverseService = metaverseService;
        this.classroomService = classroomService;
        this.personService = personService;
    }

    @GetMapping(CTRL_LECTURES)
    public String lectures(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.LECTURES);

        model.addAttribute(MODEL_LECTURES, lectureService.getAllLectures());

        return VIEW_LECTURES;
    }

    @GetMapping(CTRL_LECTURES + CTRL_NEW)
    public String lectureForm(Model model,
                              @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.LECTURES);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());
        model.addAttribute(MODEL_CLASSROOMS, classroomService.getAllClassrooms());
        model.addAttribute(MODEL_LECTURERS, personService.getAllLecturers());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_LECTURE_FORM;
    }

    @PostMapping(CTRL_LECTURES + CTRL_NEW)
    public String lecture(Lecture lecture) {
        Metaverse metaverse = lecture.getClassroom().getMetaverse();
        if (!Objects.equals(metaverse.getId(),
                lecture.getLecturer().getMetaverse().getId())) {
            return "redirect:" + CTRL_LECTURES + CTRL_NEW + "?error";
        }

        lecture.setMetaverse(metaverse);
        if (lectureService.getLectureFromMetaverse(lecture.getName(), lecture.getDateTime(),
                lecture.getMetaverse().getName()) == null) {
            lectureService.addNewLecture(lecture);

            return "redirect:" + CTRL_LECTURES;
        }

        return "redirect:" + CTRL_LECTURES + CTRL_NEW + "?error";
    }
}
