package metacampus2.service;

import metacampus2.model.Event;
import metacampus2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceEvent implements IServiceEvent {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {

        List<Event> events = getAllEvents();

        if (!events.isEmpty()) {

            for (Event event1 : events) {

                if (event.equals(event1))
                    return null;
            }
        }

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
