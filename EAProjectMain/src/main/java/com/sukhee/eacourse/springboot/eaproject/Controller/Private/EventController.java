package com.sukhee.eacourse.springboot.eaproject.Controller.Private;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.EventDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import com.sukhee.eacourse.springboot.eaproject.Domain.Organizer;
import com.sukhee.eacourse.springboot.eaproject.Service.EventService;
import com.sukhee.eacourse.springboot.eaproject.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")

public class EventController {
    private final EventService eventService;
    private final UserService userService;
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping(path="")
    @PreAuthorize("hasRole('ADMIN')")
    public Event createEvent(@RequestBody Event event, @RequestHeader("Authorization") String token) {

        return eventService.saveEvent(event, token);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Event updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }
}
