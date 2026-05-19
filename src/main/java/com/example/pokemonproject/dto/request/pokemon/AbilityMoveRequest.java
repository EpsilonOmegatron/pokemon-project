package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.enums.UpdateAction;

import java.util.List;

public record AbilityMoveRequest(List<String> abilitiesOrMoves, UpdateAction action) {
}
