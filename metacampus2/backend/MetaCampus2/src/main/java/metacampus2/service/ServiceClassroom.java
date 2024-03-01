package metacampus2.service;

import metacampus2.model.*;
import metacampus2.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceClassroom implements IServiceClassroom {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public Classroom addClassroomByOthers(Classroom classroom, Lecturer lecturer, Student student, Event event, Lecture lecture, Metaverse metaverse) {

        lecturer.getClassroomList().add(classroom);
        student.getClassroomStudent().add(classroom);
        event.getLocationEvent().add(classroom);
        lecture.setClassroom(classroom);
        metaverse.getClassrooms().add(classroom);

        classroom.getLecturerList().add(lecturer);
        classroom.getStudentList().add(student);
        classroom.setEvent(event);
        classroom.getLectureList().add(lecture);
        classroom.getMetaverseClassroom().add(metaverse);

        return classroomRepository.save(classroom);
    }

    @Override
    public Optional<Classroom> findClassroom(String id) {
        return classroomRepository.findById(id);
    }

    @Override
    public Optional<Classroom> updateClassroom(Classroom classroom, String id) {

        Optional<Classroom> checkClassroom = classroomRepository.findById(id);

        if(checkClassroom.isPresent()){

            if(!classroom.getCoordinates().isEmpty()){
                checkClassroom.get().setCoordinates(classroom.getCoordinates());
            }
            if(!classroom.getNumber().isEmpty()){
                checkClassroom.get().setNumber(classroom.getNumber());
            }
            if(classroom.getEvent() != null){
                checkClassroom.get().setEvent(classroom.getEvent());
            }

            classroomRepository.save(checkClassroom.get());

            return checkClassroom;
        }

        return Optional.empty();
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }
}
