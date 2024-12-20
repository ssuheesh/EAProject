package com.sukhee.eacourse.springboot.eaproject.JMS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sukhee.eacourse.springboot.eaproject.Domain.Payment;
import com.sukhee.eacourse.springboot.eaproject.Domain.Ticket;
import com.sukhee.eacourse.springboot.eaproject.Service.PaymentService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Consumer {
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    public Consumer(ObjectMapper objectMapper, Producer producer, PaymentService paymentService) {
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
    }

    @JmsListener(destination = "afterPaymentQueue")
    public void receiveMessage(Map<String, Object> message) {
        try {
            HashMap<String, Object> data = objectMapper.convertValue(message, HashMap.class);
            Payment payment = objectMapper.convertValue(data, Payment.class);
            paymentService.updatePaymentAndCreateTickets(payment);
            System.out.println("Payment processed successfully: " + payment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Payment convertToPayment(HashMap<String, Object> data) {
        try {
            return objectMapper.convertValue(data, Payment.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to convert data to Payment", e);
        }
    }


}
