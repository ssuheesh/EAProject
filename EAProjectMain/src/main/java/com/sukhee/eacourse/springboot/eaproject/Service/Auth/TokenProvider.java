package com.sukhee.eacourse.springboot.eaproject.Service.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TokenProvider {

    @Value("${security.jwt.secret-key}")
    private String secret;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.expiry-time-in-seconds}")
    private Long expiryTimeInSeconds;
//    @Autowired
//    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    public String createToken(Authentication authentication) {
        String username = authentication.getName();
        List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.
                builder()
                .issuer(issuer)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (expiryTimeInSeconds * 1000)))
                .claim("username", username)
                .claim("authorities", authorities)
                .signWith(getSecretKey()).compact();
    }

    public Authentication setAuthentication(String token) {
        Claims payload = parseClaimsFromToken(token);
        String username = payload.getSubject();
        ArrayList<String> authorities = payload.get("authorities", ArrayList.class);
        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).toList();
        return new UsernamePasswordAuthenticationToken(username, "", grantedAuthorities);
    }

    public boolean validateToken(String token) {
        try {
            if (tokenBlacklistService.isTokenBlacklisted(token)) {
                return false; // The token has been invalidated
            }
            parseClaimsFromToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public void destroyToken(String token) {
        tokenBlacklistService.addToBlacklist(token); // Add token to the blacklist
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims parseClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build().parseSignedClaims(token).getPayload();
    }
}