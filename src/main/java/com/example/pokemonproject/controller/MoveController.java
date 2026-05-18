package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.move.CreateMoveRequest;
import com.example.pokemonproject.dto.request.move.UpdateMoveRequest;
import com.example.pokemonproject.dto.response.MoveResponse;
import com.example.pokemonproject.service.MoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moves")
@RequiredArgsConstructor
public class MoveController {

    private final MoveService moveService;

    @GetMapping
    public ResponseEntity<List<MoveResponse>> getAllMoves() {
        return ResponseEntity.ok(moveService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<MoveResponse> getMoveByName(@PathVariable String name) {
        return ResponseEntity.ok(moveService.getMoveByName(name));
    }

    @PostMapping
    public ResponseEntity<MoveResponse> createMove(@RequestBody CreateMoveRequest request) {
        return ResponseEntity.ok(moveService.save(request));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteMoveByName(@PathVariable String name) {
        return ResponseEntity.ok(moveService.delete(name));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<MoveResponse> updateMoveByName(@PathVariable String name, @RequestBody UpdateMoveRequest request) {
        return ResponseEntity.ok(moveService.update(name, request));
    }
}
