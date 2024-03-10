package metacampus2.service;

import metacampus2.model.Event;
import metacampus2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService implements IEventService {
    private EventRepository eventRepository;


    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void addNewEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event getEventFromMetaverse(String eventName, LocalDateTime eventDateTime, String metaverseName) {
        return eventRepository.findByNameAndDateTimeAndMetaverseName(eventName, eventDateTime, metaverseName);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsFromMetaverseStartingFromCurrentTime(String metaverseName) {
        return eventRepository.findAllByMetaverseNameStartingFromCurrentTime(metaverseName);
    }
}


