package metacampus2.controller;

import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.service.IDisplayPanelService;
import metacampus2.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class ImageController extends MainController {
    protected static final String MODEL_IMAGES = "images";
    protected static final String MODEL_IMAGE = "image";
    protected static final String VIEW_IMAGES = "images";
    protected static final String VIEW_IMAGE_FORM = "image-form";

    private IImageService imageService;
    private IDisplayPanelService displayPanelService;


    @Autowired
    public ImageController(IImageService imageService, IDisplayPanelService displayPanelService) {
        this.imageService = imageService;
        this.displayPanelService = displayPanelService;
    }

    @GetMapping(CTRL_IMAGES)
    public String images(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.IMAGE);

        model.addAttribute(MODEL_IMAGES, imageService.getAllImages());

        return VIEW_IMAGES;
    }

    @GetMapping(CTRL_IMAGES + CTRL_NEW)
    public String imageForm(Model model,
                           @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.IMAGE);

        Image image = new Image();
        model.addAttribute(MODEL_IMAGE, image);

        model.addAttribute(DisplayPanelController.MODEL_DISPLAY_PANELS, displayPanelService.getAllDisplayPanels());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_IMAGE_FORM;
    }

    @PostMapping(CTRL_IMAGES + CTRL_NEW)
    public String newImage(Image image, @RequestParam(value = "imageFile") MultipartFile imageFile,
                           @RequestParam(value = "imageIndexes") List<Integer> imageIndexes) {
        if(imageFile != null && !imageFile.isEmpty()) {
            if(imageService.getImageByTitle(image.getTitle()) != null) {
                return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + CTRL_NEW + "?error=an image with this title already exists";
            }

            String imageFileName = imageFile.getOriginalFilename();
            for (DisplayPanel displayPanel: image.getDisplayPanels()) {
                if(!imageService.createFile(image, imageFile, displayPanel)) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + CTRL_NEW
                            + "?error=error while copying image file into resources folder";
                }
            }

            image.setFileName(imageFileName);
            image.setImageIndexes(imageIndexes);
            imageService.addNewImage(image);

            return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + CTRL_NEW + "?error=image file is null or empty";
    }
}
