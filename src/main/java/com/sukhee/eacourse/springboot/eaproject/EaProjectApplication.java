package com.sukhee.eacourse.springboot.eaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EaProjectApplication.class, args);
    }
    
}
