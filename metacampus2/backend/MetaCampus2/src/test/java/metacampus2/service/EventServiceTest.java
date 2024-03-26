package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Event;
import metacampus2.repository.IEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest extends AbstractTest {

    @Mock
    private IEventRepository eventRepository;

    private EventService eventService;

    @BeforeEach
    public void setUp() {
        eventService = new EventService(eventRepository);
    }

    @Test
    void getEventFromMetaverse() {

        Event event = new Event();
        event.setName("Open Day");

        when(eventRepository.findByNameAndDateTimeAndMetaverseName(Mockito.anyString(),any(LocalDateTime.class),Mockito.anyString())).thenReturn(event);

        assertEquals(event,eventService.getEventFromMetaverse("Open Day", LocalDateTime.now(),"Campus Est SUPSI"));

    }

    @Test
    void getAllEvents() {

        List<Event> eventList = new ArrayList<>();
        Event event = new Event();
        event.setName("Open Day");

        eventList.add(event);
        eventList.add(new Event());

        when(eventRepository.findAll()).thenReturn(eventList);

        assertEquals(eventList.size(), eventService.getAllEvents().size());

    }

    @Test
    void getAllEventsFromMetaverseStartingFromCurrentTime() {

        List<Event> eventList = new ArrayList<>();
        Event event = new Event();
        event.setName("Open Day");

        eventList.add(event);
        eventList.add(new Event());
        eventList.add(new Event());

        when(eventRepository.findAllByMetaverseNameStartingFromCurrentTime(Mockito.anyString())).thenReturn(eventList);

        assertEquals(event.getName(), eventService.getAllEventsFromMetaverseStartingFromCurrentTime("Campus Est SUPSI").get(0).getName());

    }
}