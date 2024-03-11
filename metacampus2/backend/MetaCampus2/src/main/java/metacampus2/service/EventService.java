package metacampus2.service;

import metacampus2.model.Event;
import metacampus2.repository.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService implements IEventService {
    private IEventRepository IEventRepository;


    @Autowired
    public EventService(IEventRepository IEventRepository) {
        this.IEventRepository = IEventRepository;
    }

    @Override
    public void addNewEvent(Event event) {
        IEventRepository.save(event);
    }

    @Override
    public Event getEventFromMetaverse(String eventName, LocalDateTime eventDateTime, String metaverseName) {
        return IEventRepository.findByNameAndDateTimeAndMetaverseName(eventName, eventDateTime, metaverseName);
    }

    @Override
    public List<Event> getAllEvents() {
        return IEventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsFromMetaverse(String metaverseName) {
        return IEventRepository.findAllByMetaverseName(metaverseName);
    }
}


