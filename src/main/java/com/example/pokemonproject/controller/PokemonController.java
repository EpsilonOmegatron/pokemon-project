package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.pokemon.AbilityMoveRequest;
import com.example.pokemonproject.dto.request.pokemon.CreatePokemonRequest;
import com.example.pokemonproject.dto.request.pokemon.UpdatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.service.PokemonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonResponse>> getPokemon() {
        return ResponseEntity.ok(pokemonService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<PokemonResponse> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<PokemonResponse> registerNewPokemon(@RequestBody @Valid CreatePokemonRequest request) {
        return ResponseEntity.ok(pokemonService.save(request));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<PokemonResponse> updatePokemon(@PathVariable String name, @RequestBody @Valid UpdatePokemonRequest request) {
        return ResponseEntity.ok(pokemonService.update(name, request));
    }

    @PatchMapping("/{name}/moves")
    public ResponseEntity<PokemonResponse> manageMoves(@PathVariable String name, @RequestBody @Valid AbilityMoveRequest request) {
        return ResponseEntity.ok(pokemonService.manageMoves(name, request));
    }

    @PatchMapping("/{name}/abilities")
    public ResponseEntity<PokemonResponse> manageAbilities(@PathVariable String name, @RequestBody @Valid AbilityMoveRequest request) {
        return ResponseEntity.ok(pokemonService.manageAbilities(name, request));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletePokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.delete(name));
    }
}
