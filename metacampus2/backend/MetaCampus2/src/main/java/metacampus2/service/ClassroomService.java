/*
package metacampus2.service;

import metacampus2.model.*;
import metacampus2.repository.IClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService implements IClassroomService {
    private IClassroomRepository IClassroomRepository;

    @Autowired
    public ClassroomService(IClassroomRepository IClassroomRepository) {
        this.IClassroomRepository = IClassroomRepository;
    }

    @Override
    public void addNewClassroom(Classroom classroom) {
        IClassroomRepository.save(classroom);
    }

    @Override
    public Classroom getClassroomFromMetaverse(String classroomNumber, String metaverseName) {
        return IClassroomRepository.findByNumberAndMetaverseName(classroomNumber, metaverseName);
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return IClassroomRepository.findAll();
    }

    @Override
    public List<Classroom> getAllClassroomsFromMetaverse(String metaverseName) {
        return IClassroomRepository.findAllByMetaverseName(metaverseName);
    }
}
*/
