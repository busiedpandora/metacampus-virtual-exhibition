package metacampus2.service;

import metacampus2.model.Lecturer;
import metacampus2.model.Metaverse;
import metacampus2.model.Office;

import java.util.List;
import java.util.Optional;

public interface IServiceLecturer {

    Lecturer addLecturerByOthers(Lecturer lecturer, Office office, Metaverse metaverse);

    Optional<Lecturer> findLecturer(String id);

    Optional<Lecturer> updateLecturer(Lecturer lecturer, String id);

    List<Lecturer> getAllLecturers();
}
