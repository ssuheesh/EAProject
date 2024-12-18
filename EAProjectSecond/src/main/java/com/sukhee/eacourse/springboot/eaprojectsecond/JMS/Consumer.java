package com.sukhee.eacourse.springboot.eaprojectsecond.JMS;

import org.springframework.jms.annotation.JmsListener;

import java.util.Map;

public class Consumer {
    @JmsListener(destination = "paymentQueue")
    public void receiveMessage(Map<String, ?> message) {
        System.out.println("Received message: " + message);
        //TODO: send back a Queue back that says processed and other service will make it successful, paid and will create ticket for me.
    }
}
