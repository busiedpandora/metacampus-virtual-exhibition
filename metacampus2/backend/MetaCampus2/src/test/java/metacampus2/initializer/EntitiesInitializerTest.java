package metacampus2.initializer;

import metacampus2.model.Metaverse;
import metacampus2.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntitiesInitializerTest {
    private EntitiesInitializer entitiesInitializer;

    @Mock
    private IMetaverseService metaverseService;
    @Mock
    private IClassroomService classroomService;
    @Mock
    private IOfficeService officeService;


    @BeforeEach
    void setUp() {
        entitiesInitializer = new EntitiesInitializer(metaverseService, classroomService, officeService);
    }


    @Test
    void runByCreatingMetaverse() throws Exception {
        when(metaverseService.getMetaverse(anyString())).thenReturn(null);

        entitiesInitializer.run();

        verify(metaverseService, times(1)).addNewMetaverse(any());
    }

    @Test
    void runByCreatingClassrooms() throws Exception {
        when(metaverseService.getMetaverse(anyString())).thenReturn(new Metaverse());
        when(classroomService.getClassroomFromMetaverse(anyString(), anyString())).thenReturn(null);

        entitiesInitializer.run();

        verify(classroomService, times(2)).addNewClassroom(any());
    }

    @Test
    void runByCreatingOffice() throws Exception {
        when(metaverseService.getMetaverse(anyString())).thenReturn(new Metaverse());
        when(officeService.getOfficeFromMetaverse(anyString(), anyString())).thenReturn(null);

        entitiesInitializer.run();

        verify(officeService, times(1)).addNewOffice(any());
    }
}