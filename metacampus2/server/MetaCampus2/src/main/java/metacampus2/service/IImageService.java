package metacampus2.service;

import metacampus2.model.Image;

import java.util.List;

public interface IImageService {
    void addNewImage(Image image);
    List<Image> getAllImages();
}
