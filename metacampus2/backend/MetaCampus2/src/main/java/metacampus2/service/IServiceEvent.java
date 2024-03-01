package metacampus2.service;

import metacampus2.model.*;

import java.util.List;
import java.util.Optional;

public interface IServiceEvent {

    Event addEventByMetaverse(Event event, Metaverse metaverse)

    Optional<Event> findEvent(String id);

    Optional<Event> updateEvent(Event event, String id);

    List<Event> getAllEvents();
}
