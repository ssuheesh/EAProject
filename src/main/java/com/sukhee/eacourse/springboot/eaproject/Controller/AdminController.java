package com.sukhee.eacourse.springboot.eaproject.Controller;

import com.sukhee.eacourse.springboot.eaproject.Domain.User;
import com.sukhee.eacourse.springboot.eaproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

}
