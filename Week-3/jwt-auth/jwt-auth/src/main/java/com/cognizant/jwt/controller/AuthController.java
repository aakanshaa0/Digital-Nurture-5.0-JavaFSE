package com.cognizant.jwt.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Map<String, String> map = new HashMap<>();

        if (authHeader == null) {
            map.put("error", "Authorization header missing");
            return map;
        }

        String token = null;

        // Agar Bearer Token hai toh use verify karo
        if (authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            String user = getUserFromToken(token);
            if (user != null) {
                map.put("token", "Valid token for user: " + user);
                return map;
            } else {
                map.put("error", "Invalid token");
                return map;
            }
        }

        // Agar Basic Auth hai toh token generate karo
        if (authHeader.startsWith("Basic ")) {
            String user = getUser(authHeader);
            String newToken = generateJwt(user);
            map.put("token", newToken);
            return map;
        }

        map.put("error", "Unsupported authorization type");
        return map;
    }

    private String getUser(String authHeader) {
        String encodedCredentials = authHeader.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decodedBytes);
        return credentials.split(":")[0];
    }

    private String getUserFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    private String generateJwt(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1200000))
                .signWith(SIGNING_KEY)
                .compact();
    }
}