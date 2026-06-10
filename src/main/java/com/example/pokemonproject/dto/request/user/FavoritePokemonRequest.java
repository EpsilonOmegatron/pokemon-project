package com.example.pokemonproject.dto.request.user;

import com.example.pokemonproject.common.enums.UpdateAction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FavoritePokemonRequest(

        @NotEmpty(message = "{validation.required}")
        List<String> pokemon,

        @NotNull(message = "{validation.action.required}")
        UpdateAction action
) {
}
