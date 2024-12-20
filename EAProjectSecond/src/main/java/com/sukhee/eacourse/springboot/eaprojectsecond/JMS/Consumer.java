package com.sukhee.eacourse.springboot.eaprojectsecond.JMS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sukhee.eacourse.springboot.eaprojectsecond.Enum.PaymentState;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Consumer {
    private final ObjectMapper objectMapper;
    private final Producer producer;

    public Consumer(ObjectMapper objectMapper, Producer producer) {
        this.objectMapper = objectMapper;
        this.producer = producer;
    }

    @JmsListener(destination = "paymentQueue")
    public void receiveMessage(String message) {
        try {
            System.out.println("Received message: " + message);// Convert JSON String to HashMap
            Map<String, Object> data = objectMapper.readValue(message, new TypeReference<HashMap<String, Object>>() {});

            // Modify the paymentState
            data.put("paymentState", PaymentState.SUCCESS);
            producer.sendAfterPaymentMessage(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
