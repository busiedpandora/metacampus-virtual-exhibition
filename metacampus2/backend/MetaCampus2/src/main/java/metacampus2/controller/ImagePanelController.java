package metacampus2.controller;

import metacampus2.service.TextPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(MainController.CTRL_SPACES)
public class ImagePanelController extends MainController {
    protected static final String MODEL_IMAGE_PANELS = "imagePanels";

    protected static final String VIEW_IMAGE_PANELS = "image-panels";
    //private TextPanelService textPanelService;


    @Autowired
    public ImagePanelController(TextPanelService noticeBoardPanelService) {
        //this.textPanelService = noticeBoardPanelService;
    }


    @GetMapping(CTRL_IMAGE_PANELS)
    public String textPanels(Model model) {

        //model.addAttribute(MODEL_TEXT_PANELS, textPanelService.getAllTextPanels());

        return VIEW_IMAGE_PANELS;
    }
}
