package com.example.pokemonproject.dto.request.ability;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAbilityRequest(

        @NotBlank(message = "{validation.required}")
        @Size(min = 5, max = 50, message = "{validation.range}")
        String name,

        @NotBlank(message = "{validation.required}")
        @Size(min = 10, max = 100, message = "{validation.range}")
        String description
) {
}
