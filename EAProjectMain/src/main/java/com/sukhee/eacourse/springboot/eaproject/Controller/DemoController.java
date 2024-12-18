package com.sukhee.eacourse.springboot.eaproject.Controller;

import com.sukhee.eacourse.springboot.eaproject.Service.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @Autowired
    Demo demoService;
    @RequestMapping(method = RequestMethod.GET, path = "/demo/{name}")
    public String demo(@PathVariable String name) {
        return demoService.sayHello(name);
    }

    @PostMapping(path = "/demo", params = {"val1", "val2"})
    public String demo2(){
        return "Hello World";
    }
}
