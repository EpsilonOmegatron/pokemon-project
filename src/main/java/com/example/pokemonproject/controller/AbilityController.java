package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.CreateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.service.AbilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abilities")
@AllArgsConstructor
public class AbilityController {

    private AbilityService abilityService;

    @GetMapping
    public ResponseEntity<List<AbilityResponse>> getAbilities() {
        return ResponseEntity.ok(abilityService.getAllAbilities());
    }

    @GetMapping("/{name}")
    public ResponseEntity<AbilityResponse> getAbilityByName(@PathVariable String name) {
        return ResponseEntity.ok(abilityService.getAbilityByName(name));
    }

    @PostMapping
    public ResponseEntity<AbilityResponse> createAbility(@RequestBody CreateAbilityRequest abilityRequest) {
        return ResponseEntity.ok(abilityService.saveAbility(abilityRequest));
    }
}
