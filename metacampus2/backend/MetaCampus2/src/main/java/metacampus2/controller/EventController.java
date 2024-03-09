package metacampus2.controller;

import metacampus2.model.Event;
import metacampus2.model.MenuItem;
import metacampus2.service.IEventService;
import metacampus2.service.IMetaverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController extends MainController {
    private static final String CTRL_EVENTS = "/events";
    private static final String VIEW_EVENTS = "events";
    private static final String VIEW_EVENT_FORM = "event-form";

    private IEventService eventService;
    private IMetaverseService metaverseService;


    @Autowired
    public EventController(IEventService eventService, IMetaverseService metaverseService) {
        this.eventService = eventService;
        this.metaverseService = metaverseService;
    }

    @GetMapping(CTRL_EVENTS)
    public String events(Model model) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.EVENTS);

        model.addAttribute(MODEL_EVENTS, eventService.getAllEvents());

        return VIEW_EVENTS;
    }

    @GetMapping(CTRL_EVENTS + CTRL_NEW)
    public String eventForm(Model model,
                            @RequestParam(value = "error", required = false) String error) {
        model.addAttribute(MODEL_MENU_ITEM, MenuItem.EVENTS);

        model.addAttribute(MODEL_METAVERSES, metaverseService.getAllMetaverses());

        model.addAttribute(MODEL_ERROR, error);

        return VIEW_EVENT_FORM;
    }

    @PostMapping(CTRL_EVENTS + CTRL_NEW)
    public String event(Event event) {
        if(eventService.getEventFromMetaverse(event.getName(),
                event.getDateTime(), event.getMetaverse().getName()) == null) {
            eventService.addNewEvent(event);

            return "redirect:" + CTRL_EVENTS;
        }

        return "redirect:" + CTRL_EVENTS + CTRL_NEW + "?error";
    }
}
