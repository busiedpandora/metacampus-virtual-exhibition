package metacampus2.service;

import metacampus2.model.Event;
import metacampus2.model.Metaverse;
import metacampus2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class ServiceEvent implements IServiceEvent {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addEventByMetaverse(Event event, Metaverse metaverse) {

        metaverse.setEventMetaverse(event);

        event.setMetaverseEvent(metaverse);

        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findEvent(String id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> updateEvent(Event event, String id) {

        Optional<Event> checkEvent = eventRepository.findById(id);

        if(checkEvent.isPresent()){

            if(!event.getCoordinates().isEmpty()){
                checkEvent.get().setCoordinates(event.getCoordinates());
            }
            if(event.getDateTime() != null){
                checkEvent.get().setDateTime(event.getDateTime());
            }

            eventRepository.save(checkEvent.get());

            return checkEvent;
        }

        return Optional.empty();
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
