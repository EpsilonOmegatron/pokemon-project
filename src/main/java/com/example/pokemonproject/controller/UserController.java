package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.user.FavoritePokemonRequest;
import com.example.pokemonproject.dto.request.user.UpdateUserRequest;
import com.example.pokemonproject.dto.response.UserResponse;
import com.example.pokemonproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{username}/favorites")
    public ResponseEntity<UserResponse> manageFavoritePokemon(@PathVariable String username, @RequestBody @Valid FavoritePokemonRequest request) {
        return ResponseEntity.ok(userService.manageFavorites(username, request));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PatchMapping("/{username}")
    public ResponseEntity<UserResponse> update(@PathVariable String username, @RequestBody @Valid UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(username, request));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{username}")
    public ResponseEntity<String> delete(@PathVariable String username) {
        return ResponseEntity.ok(userService.delete(username));
    }
}
