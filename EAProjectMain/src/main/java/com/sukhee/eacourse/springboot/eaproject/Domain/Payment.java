package com.sukhee.eacourse.springboot.eaproject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sukhee.eacourse.springboot.eaproject.Domain.Enum.PaymentState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @Setter
    private Double amount;

    @Setter
    private PaymentState paymentState;

    @Setter
    @ManyToOne
    private Participant participant;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Event event;

    @Setter
    private int quantity;

    public void addTicket(Ticket ticket) {
        if(this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", ticket=" + tickets +
                ", amount=" + amount +
                '}';
    }
}
