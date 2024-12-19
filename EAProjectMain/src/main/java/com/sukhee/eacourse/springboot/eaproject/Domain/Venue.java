package com.sukhee.eacourse.springboot.eaproject.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Setter
    private String location;

    @Getter@Setter
    @OneToMany(mappedBy = "venue", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    List<Event> events;

    public void addEvent(Event event) {
        if(events == null) events = new ArrayList<>();
        events.add(event);
    }
}
