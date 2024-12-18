package com.sukhee.eacourse.springboot.eaproject.Service;

import org.springframework.stereotype.Service;

@Service("DemoService")
public class Demo {
    public String sayHello(String name) {
        return String.format("Hello World! Demo: %s!", name);
    }
}
