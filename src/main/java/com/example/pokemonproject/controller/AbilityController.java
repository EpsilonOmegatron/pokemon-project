package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.ability.CreateAbilityRequest;
import com.example.pokemonproject.dto.request.ability.UpdateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.service.AbilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abilities")
@RequiredArgsConstructor
public class AbilityController {

    private final AbilityService abilityService;

    @GetMapping
    public ResponseEntity<List<AbilityResponse>> getAbilities() {
        return ResponseEntity.ok(abilityService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<AbilityResponse> getAbilityByName(@PathVariable String name) {
        return ResponseEntity.ok(abilityService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<AbilityResponse> createAbility(@RequestBody @Valid CreateAbilityRequest request) {
        return ResponseEntity.ok(abilityService.save(request));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteAbilityByName(@PathVariable String name) {
        return ResponseEntity.ok(abilityService.delete(name));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<AbilityResponse> updateAbilityByName(@PathVariable String name, @RequestBody @Valid UpdateAbilityRequest request) {
        return ResponseEntity.ok(abilityService.update(name, request));
    }
}
