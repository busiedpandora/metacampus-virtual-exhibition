package metacampus2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public class MainController {
    protected static final String CTRL_RESOURCES = "/resources";
    protected static final String CTRL_SPACES = "/spaces";
    protected static final String CTRL_METAVERSES = "/metaverses";
    protected static final String CTRL_TEXT_PANELS = "/text-panels";
    protected static final String CTRL_DISPLAY_PANELS = "/display-panels";
    protected static final String CTRL_AUDIO_PANELS = "/audio-panels";
    protected static final String CTRL_TEXTS = "/texts";
    protected static final String CTRL_IMAGES = "/images";


    protected static final String CTRL_NEW = "/new";
    //protected static final String MODEL_MENU_ITEM = "menuItem";
    protected static final String MODEL_MENU_CATEGORY = "menuCategory";
    protected static final String MODEL_MENU_ENTITY = "menuEntity";
    protected static final String MODEL_PROJECT_NAME = "projectName";
    protected static final String MODEL_ERROR = "error";
    protected static final String MODEL_METAVERSES = "metaverses";
    protected static final String MODEL_CLASSROOMS = "classrooms";
    protected static final String MODEL_OFFICES = "offices";
    protected static final String MODEL_EVENTS = "events";
    protected static final String MODEL_LECTURES = "lectures";
    protected static final String MODEL_PEOPLE = "people";

    @Value("${project.name}")
    private String projectName;


    @ModelAttribute
    public void addDefaultAttributes(Model model) {
        model.addAttribute(MODEL_PROJECT_NAME, projectName);
    }
}
