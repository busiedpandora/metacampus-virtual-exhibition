package metacampus2.repository;

import metacampus2.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByNameAndDateTimeAndMetaverseName(String eventName, LocalDateTime eventDateTime, String metaverseName);
    @Query("SELECT e FROM Event e WHERE e.dateTime >= CURRENT_TIMESTAMP")
    List<Event> findAllByMetaverseNameStartingFromCurrentTime(String metaverseName);
}
