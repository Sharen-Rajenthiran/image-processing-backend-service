package com.example.image_processing_service.controllers;

import com.example.image_processing_service.models.User;
import com.example.image_processing_service.models.UserResponse;
import com.example.image_processing_service.security.JwtUtil;
import com.example.image_processing_service.services.UserStore;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthenticationController {
    private final UserStore userStore;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthenticationController(UserStore userStore,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil,
                          AuthenticationManager authManager) {
        this.userStore = userStore;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    public static class AuthenticationRequest {
        public String username;
        public String password;
        // getters/setters or public fields
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest req) {
        if (req.username == null || req.password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "username and password required"));
        }
        if (userStore.findByUsername(req.username).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "username taken"));
        }
        User user = new User();
        user.setUsername(req.username);
        user.setPassword(passwordEncoder.encode(req.password));
        User saved = userStore.save(user);

        String token = jwtUtil.generateToken(saved.getUsername());
        UserResponse resp = new UserResponse(saved.getId(), saved.getUsername());
        return ResponseEntity.ok(Map.of("user", resp, "token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest req) {
        if (req.username == null || req.password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "username and password required"));
        }
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username, req.password));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "invalid credentials"));
        }
        User user = userStore.findByUsername(req.username).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername());
        UserResponse resp = new UserResponse(user.getId(), user.getUsername());
        return ResponseEntity.ok(Map.of("user", resp, "token", token));
    }
}
