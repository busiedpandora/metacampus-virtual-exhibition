package metacampus2.service;

import metacampus2.model.DisplayPanel;
import metacampus2.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    void addNewImage(Image image);
    void editImage(Image image);
    boolean createFile(Image image, MultipartFile imageFile, DisplayPanel displayPanel);
    boolean renameFile(String oldName, Image image, DisplayPanel displayPanel);
    List<Image> getAllImages();
    Image getImageByTitle(String title);
    Image getImageById(Long id);
}
