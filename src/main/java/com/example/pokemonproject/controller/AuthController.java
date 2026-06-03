package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.user.UserRequest;
import com.example.pokemonproject.dto.response.AuthResponse;
import com.example.pokemonproject.dto.response.UserResponse;
import com.example.pokemonproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
