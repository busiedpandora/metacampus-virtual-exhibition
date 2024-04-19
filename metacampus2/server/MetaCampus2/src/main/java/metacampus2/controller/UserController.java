package metacampus2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.model.Role;
import metacampus2.model.User;
import metacampus2.service.IDisplayPanelService;
import metacampus2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "login";
    }

    @GetMapping(value = "/register")
    public String registrationPageGet(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping(value = "/register")
    public String registrationPagePost(Model model, User user) {

        List<User> utenteList = userService.getAllUsers();

        Optional<User> optionalUtente = utenteList.stream().filter((ut) -> ut.equals(user)).findFirst();

        if (optionalUtente.isPresent()) {
            return "register";
        }

        model.addAttribute("user", userService.addUser(user));

        return "login";
    }

    @PostMapping("/new")
    public ResponseEntity<User> addUser(@RequestBody User user) {

        User user1 = userService.addUser(user);

        if (user1 != null) {
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        try {

            long idUtente = Long.parseLong(id);

            Optional<User> user = userService.findUser(idUtente);

            return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
