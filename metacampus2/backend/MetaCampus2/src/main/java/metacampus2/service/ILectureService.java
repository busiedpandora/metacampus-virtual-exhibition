package metacampus2.service;

import metacampus2.model.Lecture;

import java.time.LocalDateTime;
import java.util.List;


public interface ILectureService {
    void addNewLecture(Lecture lecture);

    Lecture getLectureFromMetaverse(String lectureName, LocalDateTime lectureDateTime, String metaverseName);

    List<Lecture> getAllLectures();
<<<<<<< HEAD

    List<Lecture> getAllLecturesFromMetaverse(String metaverseName);
=======
    List<Lecture> getAllLecturesFromMetaverseStartingFromCurrentTime(String metaverseName);
>>>>>>> 950f17aedde35b96d6d853c1e7187eac93f4e347
}


