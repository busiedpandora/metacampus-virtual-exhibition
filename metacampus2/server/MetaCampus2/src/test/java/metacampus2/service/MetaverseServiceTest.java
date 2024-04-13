package metacampus2.service;

import metacampus2.AbstractTest;

import metacampus2.model.Metaverse;
import metacampus2.repository.IMetaverseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

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
    void getMetaverse() {


        Metaverse metaverse = new Metaverse();
        metaverse.setName("Campus Est SUPSI");

        when(metaverseRepository.findByName(Mockito.anyString())).thenReturn(metaverse);

        assertEquals(metaverse,metaverseService.getMetaverse("Campus Est SUPSI"));

    }

    @Test
    void getAllMetaverses() {

        List<Metaverse> metaverses = new ArrayList<>();
        Metaverse metaverse = new Metaverse();
        metaverse.setName("Campus Est SUPSI");

        metaverses.add(metaverse);
        metaverses.add(new Metaverse());

        when(metaverseRepository.findAll()).thenReturn(metaverses);

        assertEquals(metaverses.size(), metaverseService.getAllMetaverses().size());

    }
}