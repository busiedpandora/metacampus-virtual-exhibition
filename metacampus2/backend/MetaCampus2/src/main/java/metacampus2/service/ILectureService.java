package metacampus2.service;

import metacampus2.model.Lecture;

import java.time.LocalDateTime;
import java.util.List;


public interface ILectureService {
    void addNewLecture(Lecture lecture);
    Lecture getLectureFromMetaverse(String lectureName, LocalDateTime lectureDateTime, String metaverseName);
    List<Lecture> getAllLectures();

    /*
    Lecture addLectureByOthers(Lecture lecture, Lecturer lecturer , Metaverse metaverse);

    Optional<Lecture> findLecture(String id);

    Optional<Lecture> updateLecture(Lecture lecture, String id);

    List<Lecture> getAllLectures();

     */
}


