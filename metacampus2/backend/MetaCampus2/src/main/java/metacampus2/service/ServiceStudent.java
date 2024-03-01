package metacampus2.service;

import metacampus2.model.Classroom;
import metacampus2.model.Metaverse;
import metacampus2.model.Student;
import metacampus2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceStudent implements IServiceStudent {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student addStudentByOthers(Student student, Classroom classroom, Metaverse metaverse) {

        classroom.getStudentList().add(student);
        metaverse.getStudents().add(student);

        student.getClassroomStudent().add(classroom);
        student.getMetaverseStudent().add(metaverse);

        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findStudent(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> updateStudent(Student student, String id) {

        Optional<Student> checkStudent = studentRepository.findById(id);

        if(checkStudent.isPresent()){

            if(!student.getFirstName().isEmpty()){
                checkStudent.get().setFirstName(student.getFirstName());
            }

            if(!student.getLastName().isEmpty()){
                checkStudent.get().setLastName(student.getLastName());
            }

            if(!student.getCardNumber().isEmpty()){
                checkStudent.get().setCardNumber(student.getCardNumber());
            }

            studentRepository.save(checkStudent.get());

            return checkStudent;
        }

        return Optional.empty();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
