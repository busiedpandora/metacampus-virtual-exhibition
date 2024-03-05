package metacampus2.service;

import metacampus2.model.*;
import metacampus2.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService implements IClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public void addNewClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Classroom getClassroomFromMetaverse(String classroomNumber, String metaverseName) {
        return classroomRepository.findByNumberAndMetaverseName(classroomNumber, metaverseName);
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public List<Classroom> getAllClassroomsFromMetaverse(String metaverseName) {
        return classroomRepository.findAllByMetaverseName(metaverseName);
    }
}
