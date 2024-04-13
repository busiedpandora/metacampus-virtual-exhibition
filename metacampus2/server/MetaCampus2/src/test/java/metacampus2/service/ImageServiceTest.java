package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Image;
import metacampus2.repository.IImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest extends AbstractTest {

    @Mock
    private IImageRepository imageRepository;
    private ImageService imageService;

    @BeforeEach
    public void setUp(){
        imageService = new ImageService(imageRepository);
    }

    @Test
    void getAllImages() {

        List<Image> imageList = new ArrayList<>();

        Image image = new Image();
        image.setName("I3A.png");

        imageList.add(image);
        imageList.add(new Image());

        when(imageRepository.findAll()).thenReturn(imageList);

        assertEquals(imageList.size(), imageService.getAllImages().size());
    }
}