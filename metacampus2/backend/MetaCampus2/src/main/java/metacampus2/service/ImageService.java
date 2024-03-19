package metacampus2.service;

import metacampus2.model.Image;
import metacampus2.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    private IImageRepository imageRepository;


    @Autowired
    public ImageService(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void addNewImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
