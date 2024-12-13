package com.sukhee.eacourse.springboot.eaproject.JMS;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "${springjms.cs544Queue}")
    public void receiveMessage(String message) {
        System.out.println("Received Message > "+message);
    }
}
