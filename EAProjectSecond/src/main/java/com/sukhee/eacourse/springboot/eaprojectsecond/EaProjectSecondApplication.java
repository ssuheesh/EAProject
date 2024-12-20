package com.sukhee.eacourse.springboot.eaprojectsecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EaProjectSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(EaProjectSecondApplication.class, args);
    }

}
