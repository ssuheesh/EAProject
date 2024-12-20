package com.sukhee.eacourse.springboot.eaproject.JMS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sukhee.eacourse.springboot.eaproject.Domain.Payment;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value(value = "${springjms.paymentQueue}")
    private String queueName;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String message) {
        System.out.println("Sending test message: " + message);
        jmsTemplate.convertAndSend(queueName, message);
    }
    public void sendPaymentProcessorMessage(Payment payment) {
        try {
            System.out.println("Sending message [paymentQueue]: " + payment.toString());
            // Convert Payment object to JSON string
            String message = objectMapper.writeValueAsString(payment);

            // Send JSON string to the queue
            jmsTemplate.convertAndSend(queueName, message, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message1) throws JMSException {
                    return message1;
                }
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
