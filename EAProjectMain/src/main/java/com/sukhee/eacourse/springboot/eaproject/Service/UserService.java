package com.sukhee.eacourse.springboot.eaproject.Service;
import com.sukhee.eacourse.springboot.eaproject.Domain.Authority;
import com.sukhee.eacourse.springboot.eaproject.Domain.Participant;
import com.sukhee.eacourse.springboot.eaproject.Domain.User;
import com.sukhee.eacourse.springboot.eaproject.Repository.AuthorityRepository;
import com.sukhee.eacourse.springboot.eaproject.Repository.UserRepository;
import com.sukhee.eacourse.springboot.eaproject.Service.Auth.TokenProvider;
import com.sukhee.eacourse.springboot.eaproject.Service.Exception.CustomNotFoundException;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {

    private static final String AUTHORITY_USER = "ROLE_USER";
    private static final String AUTHORITY_ADMIN = "ROLE_ADMIN";

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    public User registerUser(Participant user) {
        Set<Authority> authorities = new HashSet<>(Arrays.asList(getAuthority(AUTHORITY_USER)));
        user.setAuthorities(authorities);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User registerAdmin(User user) {
        Set<Authority> authorities = new HashSet<>(Arrays.asList(getAuthority(AUTHORITY_ADMIN)));
        user.setAuthorities(authorities);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User getUserByToken(String token) {
        token = token.substring(7);
        Claims claims = tokenProvider.parseClaimsFromToken(token);
        System.out.println(claims);
        Optional<User> u = userRepository.findByEmail(String.valueOf(claims.get("username")));
        if(u.isPresent()) {
            return u.get();
        } else {
            throw new CustomNotFoundException("User not found with this credentials.");
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public String loginAndGetToken(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return tokenProvider.createToken(authentication);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid username or password");
        }
    }

    public String logout(String token) {
        String jwt = token.substring(7);
        tokenProvider.destroyToken(jwt);
        return "Token invalidated successfully";
    }

    private Authority getAuthority(String authority) {
        return authorityRepository.findById(authority)
                .orElse(authorityRepository.save(new Authority(authority)));
    }
}