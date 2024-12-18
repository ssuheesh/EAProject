package com.sukhee.eacourse.springboot.eaproject.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class Event {
    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private LocalDate eventDate;

    @ManyToOne
    @Setter
    private Venue venue;

    @Setter
    @ManyToMany
    private List<Participant> participants;

    @Setter
    @ManyToOne
    private Organizer organizer;


    public void addParticipant(Participant participant) {
        if(participants == null) {
            participants = new ArrayList<>();
        }
        participants.add(participant);
    }
}
