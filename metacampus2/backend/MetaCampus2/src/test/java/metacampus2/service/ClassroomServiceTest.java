/*
package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Classroom;
import metacampus2.repository.IClassroomRepository;
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
class ClassroomServiceTest extends AbstractTest {

    @Mock
    private IClassroomRepository classroomRepository;
    private ClassroomService classroomService;

    @BeforeEach
    public void setUp(){
        classroomService = new ClassroomService(classroomRepository);
    }

    @Test
    void getClassroomFromMetaverse() {


        List<Classroom> classroomList = new ArrayList<>();

        Classroom classroom = new Classroom();
        classroom.setNumber("B3.05");
        classroomList.add(new Classroom());
        classroomList.add(classroom);

        when(classroomRepository.findByNumberAndMetaverseName(Mockito.anyString(),Mockito.anyString())).thenReturn(classroomList.get(1));

        assertEquals("B3.05", classroomService.getClassroomFromMetaverse("B3.05","Campus Est SUPSI").getNumber());
    }

    @Test
    void getAllClassrooms() {

        List<Classroom> classroomList = new ArrayList<>();

        Classroom classroom = new Classroom();
        classroom.setNumber("B3.05");
        classroomList.add(new Classroom());
        classroomList.add(classroom);

        when(classroomRepository.findAll()).thenReturn(classroomList);

        assertEquals(classroomList.size(), classroomService.getAllClassrooms().size());
    }

    @Test
    void getAllClassroomsFromMetaverse() {

        List<Classroom> classroomList = new ArrayList<>();

        Classroom classroom = new Classroom();
        classroom.setNumber("B3.05");
        classroomList.add(new Classroom());
        classroomList.add(classroom);

        when(classroomRepository.findAllByMetaverseName(Mockito.anyString())).thenReturn(classroomList);

        assertEquals("B3.05", classroomService.getAllClassroomsFromMetaverse("Campus Est SUPSI").get(1).getNumber());
    }
}*/
