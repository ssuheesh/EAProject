package com.sukhee.eacourse.springboot.eaproject.Controller;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.LoginRequestDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.User;
import com.sukhee.eacourse.springboot.eaproject.Service.UserService;
import lombok.Getter;
import lombok.Setter;
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

    @PostMapping("/users/login")
    public ResponseEntity<IdToken> authenticate(@RequestBody LoginRequestDTO loginDTO) {
        String token = userService.loginAndGetToken(loginDTO.getEmail(), loginDTO.getPassword());
        return new ResponseEntity<>(new IdToken(token), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
}

@Setter
@Getter
class IdToken {
    private String id_token;

    IdToken(String idToken) {
        id_token = idToken;
    }

}