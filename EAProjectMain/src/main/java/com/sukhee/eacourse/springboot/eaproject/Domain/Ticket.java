package com.sukhee.eacourse.springboot.eaproject.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    private Participant participant;

    @Setter
    @ManyToOne
    private Event event;
}
