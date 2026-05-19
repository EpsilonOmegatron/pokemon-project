package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.user.CreateUserRequest;
import com.example.pokemonproject.dto.request.user.FavoritePokemonRequest;
import com.example.pokemonproject.dto.request.user.UpdateUserRequest;
import com.example.pokemonproject.dto.response.UserResponse;
import com.example.pokemonproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.save(request));
    }

    @PostMapping("/{username}/favorites")
    public ResponseEntity<UserResponse> manageFavoritePokemon(@PathVariable String username, @RequestBody FavoritePokemonRequest request) {
        return ResponseEntity.ok(userService.manageFavorites(username, request));
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UserResponse> update(@PathVariable String username, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(username, request));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> delete(@PathVariable String username) {
        return ResponseEntity.ok(userService.delete(username));
    }
}
