package metacampus2.service;

import metacampus2.model.Event;
import metacampus2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventRepository eventRepository;


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
}


