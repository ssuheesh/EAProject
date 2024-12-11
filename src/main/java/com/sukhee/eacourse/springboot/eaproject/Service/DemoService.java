package com.sukhee.eacourse.springboot.eaproject.Service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public String sayHello(String name) {
        return "Hello World! Demo: " + name;
    }
}
