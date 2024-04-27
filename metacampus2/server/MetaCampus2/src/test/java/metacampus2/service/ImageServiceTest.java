package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Image;
import metacampus2.repository.IImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void addNewImage() {
        imageService.addNewImage(image);

        verify(imageRepository, times(1)).save(image);
    }

    @Test
    void editImage() {
        imageService.editImage(image);

        verify(imageRepository, times(1)).save(image);

        assertNotNull(image.getLastEditDate());
    }

    @Test
    void getAllImages() {
        when(imageRepository.findAll()).thenReturn(List.of(image));

        List<Image> images = imageService.getAllImages();

        verify(imageRepository, times(1)).findAll();

        assertEquals(image, images.get(0));
    }

    @Test
    void getImageByTitle() {
        when(imageRepository.findByTitle(image.getTitle())).thenReturn(image);

        Image i = imageService.getImageByTitle(image.getTitle());

        verify(imageRepository, times(1)).findByTitle(image.getTitle());

        assertEquals(image, i);
    }

    @Test
    void getImageById() {
        when(imageRepository.findById(image.getId())).thenReturn(Optional.of(image));

        Image i = imageService.getImageById(image.getId());

        verify(imageRepository, times(1)).findById(image.getId());

        assertEquals(image, i);
    }
}