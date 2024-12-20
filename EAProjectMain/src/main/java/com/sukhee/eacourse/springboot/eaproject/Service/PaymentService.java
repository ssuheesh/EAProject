package com.sukhee.eacourse.springboot.eaproject.Service;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.PaymentDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.Enum.PaymentState;
import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import com.sukhee.eacourse.springboot.eaproject.Domain.Participant;
import com.sukhee.eacourse.springboot.eaproject.Domain.Payment;
import com.sukhee.eacourse.springboot.eaproject.Domain.Ticket;
import com.sukhee.eacourse.springboot.eaproject.JMS.Producer;
import com.sukhee.eacourse.springboot.eaproject.Repository.PaymentRepository;
import com.sukhee.eacourse.springboot.eaproject.Repository.TicketRepository;
import com.sukhee.eacourse.springboot.eaproject.Service.Exception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final EventService eventService;
    private final Producer producer;
    private final TicketRepository ticketRepository;

    public PaymentService(PaymentRepository paymentRepository, EventService eventService, Producer producer, TicketRepository ticketRepository) {
        this.paymentRepository = paymentRepository;
        this.eventService = eventService;
        this.producer = producer;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Payment processPayment(PaymentDTO paymentdto, Participant participant) {
        Event event = eventService.getEventById(paymentdto.getEventId());
        Payment payment = new Payment();
        payment.setPaymentState(PaymentState.PROCESSING);
        payment.setAmount(event.getPrice()*paymentdto.getQuantity());
        payment.setQuantity(paymentdto.getQuantity());
        payment.setEvent(event);
        payment.setParticipant(participant);
        event.addParticipant(participant);
        paymentRepository.save(payment);
        producer.sendPaymentProcessorMessage(payment);
        return payment;
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Payment not found."));
    }

    public Payment updatePayment(Payment payment) {
        if(payment.getId() == null) throw new CustomNotFoundException("Payment not found.");
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentAndCreateTickets(Payment payment) {
        List<Ticket> tickets= new ArrayList<>();
        for(int i=0; i<payment.getQuantity(); i++) {
            Ticket t = new Ticket();
            t.setEvent(payment.getEvent());
            t.setParticipant(payment.getParticipant());
            ticketRepository.save(t);
            tickets.add(t);
        }
        payment.setTickets(tickets);
        paymentRepository.save(payment);
        return payment;
    }
}
