package com.sukhee.eacourse.springboot.eaproject.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    private Ticket ticket;

    @Setter
    private Double amount;
    @Setter
    private String paymentMethod;
}
