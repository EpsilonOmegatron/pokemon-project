package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.enums.UpdateAction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AbilityMoveRequest(
        @NotEmpty(message = "Moves or abilities are required")
        List<String> abilitiesOrMoves,

        @NotNull(message = "Specify update action")
        UpdateAction action) {
}
