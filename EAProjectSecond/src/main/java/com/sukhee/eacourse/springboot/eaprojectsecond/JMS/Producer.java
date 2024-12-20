package com.sukhee.eacourse.springboot.eaprojectsecond.JMS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value(value = "${springjms.afterPaymentQueue}")
    private String queueName;

    public void sendAfterPaymentMessage(Map<String, ?> data) {
        try {
            System.out.println("Sending message [afterPaymentQueue]: " + data);
            jmsTemplate.convertAndSend(queueName, data);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}

