package metacampus2.service;

import metacampus2.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {
    void addNewEvent(Event event);

    Event getEventFromMetaverse(String eventName, LocalDateTime eventDateTime, String metaverseName);

    List<Event> getAllEvents();
<<<<<<< HEAD

    List<Event> getAllEventsFromMetaverse(String metaverseName);
=======
    List<Event> getAllEventsFromMetaverseStartingFromCurrentTime(String metaverseName);
>>>>>>> 950f17aedde35b96d6d853c1e7187eac93f4e347
}
