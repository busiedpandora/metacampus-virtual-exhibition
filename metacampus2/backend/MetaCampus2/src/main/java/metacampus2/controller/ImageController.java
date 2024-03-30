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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class ImageController extends MainController {
    protected static final String MODEL_IMAGES = "images";
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

        model.addAttribute(DisplayPanelController.MODEL_DISPLAY_PANELS, displayPanelService.getAllDisplayPanels());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_IMAGE_FORM;
    }

    @PostMapping(CTRL_IMAGES + CTRL_NEW)
    public String newImage(Image image, @RequestParam(value = "imageFile") MultipartFile imageFile) {
        if(imageFile != null && !imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();

            for (DisplayPanel displayPanel: image.getDisplayPanels()) {
                File imagesDirectory = new File(METAVERSES_PATH + displayPanel.getMetaverse().getUrlName() +
                        SEPARATOR + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR + IMAGES_PATH);

                if(!imagesDirectory.exists()) {
                    if(!imagesDirectory.mkdirs()) {
                        return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + CTRL_NEW + "?error";
                    }
                }

                Path imagePath = Path.of(imagesDirectory.getPath() + SEPARATOR + imageName);

                try {
                    Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + CTRL_NEW + "?error";
                }
            }

            image.setName(imageName);
            imageService.addNewImage(image);

            return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + CTRL_NEW + "?error";
    }
}
