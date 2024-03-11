package metacampus2.controller;

import metacampus2.model.Classroom;
import metacampus2.model.MenuItem;
import metacampus2.service.IClassroomService;
import metacampus2.service.IMetaverseService;
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
public class ClassroomController extends MainController {
    private static final String CTRL_CLASSROOMS = "/classrooms";
    private static final String VIEW_CLASSROOMS = "classrooms";
    private static final String VIEW_CLASSROOM_FORM = "classroom-form";

    private IClassroomService classroomService;
    private IMetaverseService metaverseService;


    @Autowired
    public ClassroomController(IClassroomService classroomService, IMetaverseService metaverseService) {
        this.classroomService = classroomService;
        this.metaverseService = metaverseService;
    }

    @GetMapping(CTRL_CLASSROOMS)
    public String classrooms(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.CLASSROOMS);

        model.addAttribute(MODEL_CLASSROOMS, classroomService.getAllClassrooms());

        return VIEW_CLASSROOMS;
    }

    @GetMapping(CTRL_CLASSROOMS + "/get/{metaverseName}")
    public ResponseEntity<List<Classroom>> classrooms(@PathVariable("metaverseName") String metaverseName) {
        return new ResponseEntity<>(classroomService.getAllClassroomsFromMetaverse(metaverseName), HttpStatus.OK);
    }

    @GetMapping(CTRL_CLASSROOMS + CTRL_NEW)
    public String classroomForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.CLASSROOMS);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_CLASSROOM_FORM;
    }

    @PostMapping(CTRL_CLASSROOMS + CTRL_NEW)
    public String classroom(Classroom classroom) {
        if (classroomService.getClassroomFromMetaverse(classroom.getNumber(),
                classroom.getMetaverse().getName()) == null) {
            classroomService.addNewClassroom(classroom);

            return "redirect:" + CTRL_CLASSROOMS;
        }

        return "redirect:" + CTRL_CLASSROOMS + CTRL_NEW + "?error";
    }
}
