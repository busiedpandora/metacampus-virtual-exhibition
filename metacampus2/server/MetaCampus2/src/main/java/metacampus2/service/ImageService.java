package metacampus2.service;

import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import metacampus2.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageService extends AbstractService implements IImageService {
    private IImageRepository imageRepository;


    @Autowired
    public ImageService(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void addNewImage(Image image) {
        image.setCreationDate(LocalDateTime.now());

        imageRepository.save(image);
    }

    @Override
    public void editImage(Image image) {
        image.setLastEditDate(LocalDateTime.now());

        imageRepository.save(image);
    }

    @Override
    public boolean createFile(Image image, MultipartFile imageFile, DisplayPanel displayPanel) {
        String imageFullName = imageFile.getOriginalFilename();

        File imageDirectory = new File(METAVERSES_PATH + displayPanel.getMetaverse().getUrlName() +
                SEPARATOR + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR
                + IMAGES_PATH + image.getTitle());

        if(!imageDirectory.exists()) {
            if(!imageDirectory.mkdirs()) {
                return false;
            }
        }

        Path imagePath = Path.of(imageDirectory.getPath() + SEPARATOR + imageFullName);

        try {
            Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean renameFile(String oldName, Image image, DisplayPanel displayPanel) {
        File imageDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR + IMAGES_PATH + oldName);

        File imageRenamedDirectory = new File(METAVERSES_PATH
                + displayPanel.getMetaverse().getUrlName() + SEPARATOR
                + DISPLAY_PANELS_PATH + displayPanel.getUrlName() + SEPARATOR + IMAGES_PATH + image.getTitle());

        return imageDirectory.renameTo(imageRenamedDirectory);
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImageByTitle(String title) {
        return imageRepository.findByTitle(title);
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
