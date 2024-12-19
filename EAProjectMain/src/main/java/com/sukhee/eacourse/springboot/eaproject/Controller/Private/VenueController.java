package com.sukhee.eacourse.springboot.eaproject.Controller.Private;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.VenueDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import com.sukhee.eacourse.springboot.eaproject.Domain.Venue;
import com.sukhee.eacourse.springboot.eaproject.Service.VenueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    private final VenueService venueService;
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Venue createVenue(@RequestBody Venue venue) {
        return venueService.save(venue);
    }

    @PostMapping("/{id}/events")
    @PreAuthorize("hasRole('ADMIN')")
    public Venue createVenueEvent(@PathVariable Long id, @RequestBody Event event) {
        return venueService.addEventToVenue(id, event);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Venue updateVenue(@PathVariable Long id, @RequestBody VenueDTO venuedto) {
        return venueService.updateVenue(id, venuedto);
    }


}
