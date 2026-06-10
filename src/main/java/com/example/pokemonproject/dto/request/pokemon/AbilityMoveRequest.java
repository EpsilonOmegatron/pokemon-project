package com.example.pokemonproject.dto.request.pokemon;

import com.example.pokemonproject.common.enums.UpdateAction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AbilityMoveRequest(
        @NotEmpty(message = "{validation.required}")
        List<String> abilitiesOrMoves,

        @NotNull(message = "{validation.action.required}")
        UpdateAction action) {
}
