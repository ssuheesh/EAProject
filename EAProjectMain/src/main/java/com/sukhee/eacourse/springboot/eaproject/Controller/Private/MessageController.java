package com.sukhee.eacourse.springboot.eaproject.Controller.Private;

import com.sukhee.eacourse.springboot.eaproject.JMS.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    Producer producer;
    @GetMapping(path="/jmstest/{message}")
    public String message(@PathVariable String message) {
        producer.sendMessage(message);
        return "message";
    }
}
