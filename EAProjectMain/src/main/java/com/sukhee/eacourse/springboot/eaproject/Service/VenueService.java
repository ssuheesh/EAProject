package com.sukhee.eacourse.springboot.eaproject.Service;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.VenueDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import com.sukhee.eacourse.springboot.eaproject.Domain.Venue;
import com.sukhee.eacourse.springboot.eaproject.Repository.EventRepository;
import com.sukhee.eacourse.springboot.eaproject.Repository.VenueRepository;
import com.sukhee.eacourse.springboot.eaproject.Service.Exception.CustomNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;
    private final EventService eventService;

    public VenueService(VenueRepository venueRepository, EventRepository eventRepository, EventService eventService) {
        this.venueRepository = venueRepository;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }
    public Venue save(Venue venue) {
        return venueRepository.save(venue);
    }
    public Venue getVenueById(Long id) {
        Optional<Venue> v = venueRepository.findById(id);
        if(v.isPresent()) {
            return v.get();
        }
        throw new CustomNotFoundException("Venue Not Found");
    }


    public Venue updateVenue(Long id, VenueDTO venuedto) {
        Venue venue = getVenueById(id);
        if(venuedto.getName() != null) {
            venue.setName(venuedto.getName());
        }
        if(venuedto.getLocation() != null) {
            venue.setLocation(venuedto.getLocation());
        }
        venueRepository.save(venue);
        return venue;
    }

    @Transactional
    public Venue addEventToVenue(Long venueId, Event event, String token) {
        Venue venue = getVenueById(venueId);
        venue.addEvent(event);
        event.setVenue(venue);
        eventService.saveEvent(event, token);
        venueRepository.save(venue);
        return venue;
    }
}
