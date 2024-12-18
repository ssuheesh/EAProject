package com.sukhee.eacourse.springboot.eaproject.JMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value(value = "${springjms.paymentQueue}")
    private String queueName;
    public void sendMessage(String message) {
        System.out.println("Sending test message: " + message);
        jmsTemplate.convertAndSend(queueName, message);
    }
    public void sendPaymentProcessorMessage(Map<String, ?> message) {
        System.out.println("Sending message [paymentQueue]: " + message);
        jmsTemplate.convertAndSend(queueName, message);
    }

}
