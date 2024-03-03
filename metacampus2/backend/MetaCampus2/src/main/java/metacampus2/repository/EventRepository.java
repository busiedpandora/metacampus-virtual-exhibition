package metacampus2.repository;

import metacampus2.model.Classroom;
import metacampus2.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByNameAndDateTimeAndMetaverseName(String eventName, LocalDateTime eventDateTime, String metaverseName);
}
