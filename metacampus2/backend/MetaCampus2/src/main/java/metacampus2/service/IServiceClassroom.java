package metacampus2.service;

import metacampus2.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IServiceClassroom {

    Classroom addClassroomByOthers(Classroom classroom, Lecturer lecturer, Student student, Event event, Lecture lecture, Metaverse metaverse);

    Optional<Classroom> findClassroom(String id);

    Optional<Classroom> updateClassroom(Classroom classroom, String id);

    List<Classroom> getAllClassrooms();
}
