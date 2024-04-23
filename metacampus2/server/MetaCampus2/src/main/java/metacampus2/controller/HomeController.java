package metacampus2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends MainController {


    @GetMapping
    public String home() {
        return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES;
    }
}
