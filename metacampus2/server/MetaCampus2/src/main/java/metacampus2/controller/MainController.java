package metacampus2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.nio.file.FileSystems;

public class MainController {
    protected static final String CTRL_RESOURCES = "/resources";
    protected static final String CTRL_SPACES = "/spaces";
    protected static final String CTRL_METAVERSES = "/metaverses";
    protected static final String CTRL_TEXT_PANELS = "/text-panels";
    protected static final String CTRL_DISPLAY_PANELS = "/display-panels";
    protected static final String CTRL_TEXTS = "/texts";
    protected static final String CTRL_IMAGES = "/images";
    protected static final String CTRL_AUDIOS = "/audios";
    protected static final String CTRL_LOGIN = "/login";
    protected static final String CTRL_LOGOUT = "/logout";
    protected static final String CTRL_REGISTER = "/register";
    protected static final String CTRL_NEW = "/new";
    protected static final String CTRL_EDIT = "/edit";
    protected static final String CTRL_DELETE = "/delete";
    protected static final String MODEL_MENU_CATEGORY = "menuCategory";
    protected static final String MODEL_MENU_ENTITY = "menuEntity";
    protected static final String MODEL_PROJECT_NAME = "projectName";
    protected static final String MODEL_ERROR = "error";
    protected static final String MODEL_METAVERSES = "metaverses";
    protected static final String MODEL_TITLE = "title";
    protected static final String MODEL_FORM_HREF = "href";
    protected static final String MODEL_ON_EDIT = "onEdit";


    @Value("${project.name}")
    private String projectName;


    @ModelAttribute
    public void addDefaultAttributes(Model model) {
        model.addAttribute(MODEL_PROJECT_NAME, projectName);

        model.addAttribute("loginUrl", UserController.CTRL_LOGIN);
        model.addAttribute("registerUrl", UserController.CTRL_REGISTER);
        model.addAttribute("logoutUrl", UserController.CTRL_LOGOUT);
        model.addAttribute("manageUsersUrl", UserController.CTRL_USERS);
    }
}
