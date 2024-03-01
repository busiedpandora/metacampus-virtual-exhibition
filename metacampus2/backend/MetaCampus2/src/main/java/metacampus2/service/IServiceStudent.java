package metacampus2.service;

import metacampus2.model.*;

import java.util.List;
import java.util.Optional;

public interface IServiceStudent {

    Student addStudentByOthers(Student student, Classroom classroom, Metaverse metaverse);

    Optional<Student> findStudent(String id);

    Optional<Student> updateStudent(Student student, String id);

    List<Student> getAllStudents();
}
