package com.sukhee.eacourse.springboot.eaproject.Service;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.EventDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import com.sukhee.eacourse.springboot.eaproject.Repository.EventRepository;
import com.sukhee.eacourse.springboot.eaproject.Service.Exception.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new CustomNotFoundException("Event not found.");
        }
    }
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Event not found.111"));
        if(eventDTO.getTitle() != null) {
            event.setTitle(eventDTO.getTitle());
        }
        if(eventDTO.getEventDate() != null) {
            event.setEventDate(eventDTO.getEventDate());
        }
        eventRepository.save(event);
        return event;
    }

    public void deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
        } else {
            throw new CustomNotFoundException("Event not found.");
        }
    }
}
