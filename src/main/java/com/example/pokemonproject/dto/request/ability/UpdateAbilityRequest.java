package com.example.pokemonproject.dto.request.ability;

import jakarta.validation.constraints.Size;

public record UpdateAbilityRequest(

        @Size(min = 5, max = 50, message = "{validation.range}")
        String name,

        @Size(min = 10, max = 100, message = "{validation.range}")
        String description
) {
}
