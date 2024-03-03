package metacampus2.repository;

import metacampus2.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findByNumberAndMetaverseName(String classroomNumber, String metaverseName);
}
