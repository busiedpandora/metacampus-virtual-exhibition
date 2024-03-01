package metacampus2.service;


import metacampus2.model.Lecture;
import metacampus2.model.Lecturer;
import metacampus2.model.Metaverse;

import java.util.List;
import java.util.Optional;


public interface IServiceLecture {

    Lecture addLectureByOthers(Lecture lecture, Lecturer lecturer , Metaverse metaverse);

    Optional<Lecture> findLecture(String id);

    Optional<Lecture> updateLecture(Lecture lecture, String id);

    List<Lecture> getAllLectures();
}
