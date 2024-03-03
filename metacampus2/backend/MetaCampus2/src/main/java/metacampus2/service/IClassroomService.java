package metacampus2.service;

import metacampus2.model.*;

import java.util.List;
import java.util.Optional;

public interface IClassroomService {
    void addNewClassroom(Classroom classroom);
    Classroom getClassroomFromMetaverse(String classroomNumber, String metaverseName);
    List<Classroom> getAllClassrooms();
}
