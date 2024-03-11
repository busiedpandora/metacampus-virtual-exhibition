package metacampus2.repository;

import metacampus2.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ILectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findByNameAndDateTimeAndMetaverseName(String lectureName, LocalDateTime lectureDateTime, String metaverseName);
    List<Lecture> findAllByMetaverseName(String metaverseName);
    @Query("SELECT l FROM Lecture l WHERE l.dateTime >= CURRENT_TIMESTAMP")
    List<Lecture> findAllByMetaverseStartingFromCurrentTime(String metaverseName);
}
