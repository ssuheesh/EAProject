package com.sukhee.eacourse.springboot.eaproject.Controller;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.IdTokenDTO;
import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.LoginRequestDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.User;
import com.sukhee.eacourse.springboot.eaproject.Service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/users/register-admin")
    public User registerAdmin(@RequestBody User user) {
        return userService.registerAdmin(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<IdTokenDTO> authenticate(@RequestBody LoginRequestDTO loginDTO) {
        String token = userService.loginAndGetToken(loginDTO.getEmail(), loginDTO.getPassword());
        return new ResponseEntity<>(new IdTokenDTO(token), HttpStatus.OK);
    }

    @PostMapping("/users/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.logout(token));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
}

