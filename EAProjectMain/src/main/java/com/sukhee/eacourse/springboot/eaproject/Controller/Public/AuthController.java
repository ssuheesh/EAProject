package com.sukhee.eacourse.springboot.eaproject.Controller.Public;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.IdTokenDTO;
import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.LoginRequestDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.Organizer;
import com.sukhee.eacourse.springboot.eaproject.Domain.Participant;
import com.sukhee.eacourse.springboot.eaproject.Domain.User;
import com.sukhee.eacourse.springboot.eaproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody Participant user) {
        return userService.registerUser(user);
    }

    @PostMapping("/register-admin")
    public User registerAdmin(@RequestBody Organizer user) {
        return userService.registerAdmin(user);
    }

    @PostMapping("/login")
    public ResponseEntity<IdTokenDTO> authenticate(@RequestBody LoginRequestDTO loginDTO) {
        String token = userService.loginAndGetToken(loginDTO.getEmail(), loginDTO.getPassword());
        return new ResponseEntity<>(new IdTokenDTO(token), HttpStatus.OK);
    }
}
