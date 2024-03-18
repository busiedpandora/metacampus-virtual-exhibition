/*
package metacampus2.service;

import metacampus2.AbstractTest;

import metacampus2.model.Event;
import metacampus2.model.Lecture;
import metacampus2.repository.ILectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest extends AbstractTest {

    @Mock
    private ILectureRepository lectureRepository;

    private LectureService lectureService;

    @BeforeEach
    public void setUp(){
        lectureService = new LectureService(lectureRepository);
    }

    @Test
    void getLectureFromMetaverse() {

        Lecture lecture = new Lecture();
        lecture.setName("Mathematics");

        when(lectureRepository.findByNameAndDateTimeAndMetaverseName(Mockito.anyString(),any(LocalDateTime.class),Mockito.anyString())).thenReturn(lecture);

        assertEquals(lecture,lectureService.getLectureFromMetaverse("Mathematics", LocalDateTime.now(),"Campus Est SUPSI"));

    }

    @Test
    void getAllLectures() {

        List<Lecture> lectureList = new ArrayList<>();
        Lecture lecture = new Lecture();
        lecture.setName("Mathematics");

        lectureList.add(lecture);
        lectureList.add(new Lecture());

        when(lectureRepository.findAll()).thenReturn(lectureList);

        assertEquals(lectureList.size(), lectureService.getAllLectures().size());
    }

    @Test
    void getAllLecturesFromMetaverseStartingFromCurrentTime() {

        List<Lecture> lectureList = new ArrayList<>();
        Lecture lecture = new Lecture();
        lecture.setName("Mathematics");

        lectureList.add(lecture);
        lectureList.add(new Lecture());

        when(lectureRepository.findAllByMetaverseStartingFromCurrentTime(Mockito.anyString())).thenReturn(lectureList);

        assertEquals(lecture.getName(), lectureService.getAllLecturesFromMetaverseStartingFromCurrentTime("Campus Est SUPSI").get(0).getName());

    }
}*/
