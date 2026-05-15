package com.example.pokemonproject.controller;

import com.example.pokemonproject.dto.request.CreateMoveRequest;
import com.example.pokemonproject.dto.response.MoveResponse;
import com.example.pokemonproject.service.MoveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moves")
@AllArgsConstructor
public class MoveController {

    private MoveService moveService;

    @GetMapping
    public ResponseEntity<List<MoveResponse>> getAllMoves() {
        return ResponseEntity.ok(moveService.getAllMoves());
    }

    @GetMapping("/{name}")
    public ResponseEntity<MoveResponse> getMoveByName(@PathVariable String name) {
        return ResponseEntity.ok(moveService.getMoveByName(name));
    }

    @PostMapping
    public ResponseEntity<MoveResponse> createMove(@RequestBody CreateMoveRequest request) {
        return ResponseEntity.ok(moveService.saveMove(request));
    }
}
