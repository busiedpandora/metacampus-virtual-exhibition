package metacampus2.controller;

import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import metacampus2.model.MenuCategory;
import metacampus2.model.MenuEntity;
import metacampus2.service.IDisplayPanelService;
import metacampus2.service.IImageService;
import metacampus2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(MainController.CTRL_RESOURCES)
public class ImageController extends MainController {
    protected static final String MODEL_IMAGES = "images";
    protected static final String MODEL_IMAGE = "image";
    protected static final String VIEW_IMAGES = "images";
    protected static final String VIEW_IMAGE_FORM = "image-form";

    private IImageService imageService;
    private IDisplayPanelService displayPanelService;
    private IUserService userService;


    @Autowired
    public ImageController(IImageService imageService, IDisplayPanelService displayPanelService, IUserService userService) {
        this.imageService = imageService;
        this.displayPanelService = displayPanelService;
        this.userService = userService;
    }

    @GetMapping(CTRL_IMAGES)
    public String images(Model model) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.IMAGE);

        model.addAttribute(MODEL_IMAGES, imageService.getAllImages());

        return VIEW_IMAGES;
    }

    @GetMapping(CTRL_IMAGES + CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
    public String newImageForm(Model model,
                           @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.IMAGE);

        Image image = new Image();
        model.addAttribute(MODEL_IMAGE, image);
        model.addAttribute(MODEL_ON_EDIT, false);

        model.addAttribute(MODEL_TITLE, "New image:");
        model.addAttribute(MODEL_FORM_HREF, "/resources/images/new");

        model.addAttribute(DisplayPanelController.MODEL_DISPLAY_PANELS, displayPanelService.getAllDisplayPanels());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_IMAGE_FORM;
    }

    @GetMapping(CTRL_IMAGES + "/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('CREATOR')")
    public String editImageForm(Model model, @PathVariable("id") Long id,
                            @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_CATEGORY, MenuCategory.RESOURCES);
        model.addAttribute(MODEL_MENU_ENTITY, MenuEntity.IMAGE);

        Image image = imageService.getImageById(id);
        model.addAttribute(MODEL_IMAGE, image);
        model.addAttribute(MODEL_ON_EDIT, true);

        model.addAttribute(MODEL_TITLE, "Edit image:");
        model.addAttribute(MODEL_FORM_HREF, "/resources/images/" + id + "/edit");

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_IMAGE_FORM;
    }

    @PostMapping(CTRL_IMAGES + CTRL_NEW)
    @PreAuthorize("hasRole('CREATOR')")
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

            image.setCreator(userService.getUserLogged());
            image.setFileName(imageFileName);
            image.setImageIndexes(imageIndexes);
            imageService.addNewImage(image);

            return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + CTRL_NEW + "?error=image file is null or empty";
    }

    @PostMapping(CTRL_IMAGES + "/{id}" + CTRL_EDIT)
    @PreAuthorize("hasRole('ADMIN') || hasRole('CREATOR') && #image.creator.username == authentication.name")
    public String editImage(@PathVariable("id") Long id, @P("image") Image image) {
        Image imageByTitle = imageService.getImageByTitle(image.getTitle());
        if(imageByTitle == null || Objects.equals(imageByTitle.getId(), id)) {
            Image imageById = imageService.getImageById(id);
            for (DisplayPanel displayPanel: image.getDisplayPanels()) {
                if(!imageService.renameFile(imageById.getTitle(), image, displayPanel)) {
                    return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + "/" + id + CTRL_EDIT
                            + "?error=error occured while renaming the image file";
                }
            }

            imageService.editImage(image);

            return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES;
        }

        return "redirect:" + CTRL_RESOURCES + CTRL_IMAGES + "/" + id + CTRL_EDIT
                + "?error=an image with this title already exists";
    }
}
