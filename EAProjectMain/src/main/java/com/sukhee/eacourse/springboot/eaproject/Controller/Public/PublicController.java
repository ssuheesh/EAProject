package com.sukhee.eacourse.springboot.eaproject.Controller.Public;

import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import com.sukhee.eacourse.springboot.eaproject.Domain.Venue;
import com.sukhee.eacourse.springboot.eaproject.Service.EventService;
import com.sukhee.eacourse.springboot.eaproject.Service.VenueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final EventService eventService;
    private final VenueService venueService;

    public PublicController(EventService eventService, VenueService venueService) {
        this.eventService = eventService;
        this.venueService = venueService;

    }

    @GetMapping(path = "/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(path = "/events/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEventById(id);
    }



    @GetMapping("/venues")
    public List<Venue> getVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/venues/{id}")
    public Venue getVenueById(@PathVariable Long id) {
        return venueService.getVenueById(id);
    }

}
