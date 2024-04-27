package metacampus2.service;

import metacampus2.AbstractTest;

import metacampus2.model.Metaverse;
import metacampus2.repository.IMetaverseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetaverseServiceTest extends AbstractTest {
    @Mock
    private IMetaverseRepository metaverseRepository;
    private MetaverseService metaverseService;


    @BeforeEach
    public void setUp(){
        metaverseService = new MetaverseService(metaverseRepository);
    }

    @Test
    void addNewMetaverse() {
        metaverseService.addNewMetaverse(metaverse);

        verify(metaverseRepository, times(1)).save(metaverse);
    }

    @Test
    void deleteMetaverse() {
        metaverseService.deleteMetaverse(metaverse);

        verify(metaverseRepository, times(1)).delete(metaverse);
    }

    @Test
    void getMetaverseByName() {
        when(metaverseRepository.findByName(metaverse.getName())).thenReturn(metaverse);

        Metaverse m = metaverseService.getMetaverseByName(metaverse.getName());

        verify(metaverseRepository, times(1)).findByName(metaverse.getName());

        assertEquals(metaverse, m);
    }

    @Test
    void getMetaverseByUrlName() {
        when(metaverseRepository.findByUrlName(metaverse.getUrlName())).thenReturn(metaverse);

        Metaverse m = metaverseService.getMetaverseByUrlName(metaverse.getUrlName());

        verify(metaverseRepository, times(1)).findByUrlName(metaverse.getUrlName());

        assertEquals(metaverse, m);
    }

    @Test
    void getAllMetaverses() {
        when(metaverseRepository.findAll()).thenReturn(List.of(metaverse));

        List<Metaverse> metaverses = metaverseService.getAllMetaverses();

        verify(metaverseRepository, times(1)).findAll();

        assertEquals(metaverse, metaverses.get(0));
    }

    @Test
    void getMetaverseById() {
        when(metaverseRepository.findById(metaverse.getId())).thenReturn(Optional.of(metaverse));

        Metaverse m = metaverseService.getMetaverseById(metaverse.getId());

        verify(metaverseRepository, times(1)).findById(metaverse.getId());

        assertEquals(metaverse, m);
    }
}