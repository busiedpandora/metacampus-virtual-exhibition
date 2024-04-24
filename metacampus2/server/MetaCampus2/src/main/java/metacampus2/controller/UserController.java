package metacampus2.controller;

import metacampus2.model.User;
import metacampus2.model.UserRole;
import metacampus2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController extends MainController {
    protected static final String CTRL_USER = "/user";
    protected static final String CTRL_USERS = "/users";
    protected static final String CTRL_SEARCH = "/search";
    protected static final String MODEL_USER = "user";
    protected static final String MODEL_USERS = "users";
    protected static final String MODEL_USER_ROLES = "roles";
    protected static final String VIEW_LOGIN = "login";
    protected static final String VIEW_REGISTRATION = "registration";
    protected static final String VIEW_MANAGE_USERS = "manage-users";

    private IUserService userService;


    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(CTRL_LOGIN)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        model.addAttribute(MODEL_ERROR, error);

        return VIEW_LOGIN;
    }

    @GetMapping(CTRL_REGISTER)
    public String registrationPage(@RequestParam(value = "error", required = false) String error,
                                   Model model) {
        User user = new User();
        model.addAttribute(MODEL_USER, user);

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_REGISTRATION;
    }

    @PostMapping(CTRL_REGISTER)
    public String registration(User user) {

        if(userService.getUser(user.getUsername()) == null) {
            user.setRole(UserRole.ROLE_VISITOR);
            userService.addUser(user);
            return "redirect:" + CTRL_LOGIN;
        }

        return "redirect:" + CTRL_REGISTER + "?error";
    }

    @GetMapping(CTRL_USER + CTRL_SEARCH)
    public ResponseEntity<User> searchUser(@RequestParam("username") String username) {
        User user = userService.getUser(username);

        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(CTRL_USERS)
    @PreAuthorize("hasRole('ADMIN')")
    public String manageUsers(Model model) {
        model.addAttribute(MODEL_USERS, userService.getAllUsers());

        model.addAttribute(MODEL_USER_ROLES, UserRole.getAllUserRoles());

        return VIEW_MANAGE_USERS;
    }

    @PostMapping(CTRL_USERS)
    @PreAuthorize("hasRole('ADMIN')")
    public String manageUsers(@RequestParam("usernames") String[] usernames,
                              @RequestParam("roles") UserRole[] roles) {

        for(int i = 0; i < usernames.length; i++) {
            if(roles.length > i) {
                User user = userService.getUser(usernames[i]);
                if(user != null && user.getRole() != roles[i]) {
                    user.setRole(roles[i]);

                    userService.editUser(user);
                }
            }
        }

        return "redirect:" + CTRL_USERS;
    }
}
