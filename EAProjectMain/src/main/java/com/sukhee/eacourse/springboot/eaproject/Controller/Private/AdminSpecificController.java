package com.sukhee.eacourse.springboot.eaproject.Controller.Private;

import com.sukhee.eacourse.springboot.eaproject.Domain.User;
import com.sukhee.eacourse.springboot.eaproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminSpecificController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

}
