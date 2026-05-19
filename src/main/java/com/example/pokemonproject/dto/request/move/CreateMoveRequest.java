package com.example.pokemonproject.dto.request.move;

import com.example.pokemonproject.enums.DamageCategory;
import com.example.pokemonproject.enums.Type;
import jakarta.validation.constraints.*;

public record CreateMoveRequest(

        @NotBlank(message = "Move name is required")
        @Size(max = 50, message = "Move name must be <= 50 characters")
        String name,

        @NotBlank(message = "Move description is required")
        @Size(max = 100, message = "Move description must be <= 100 characters")
        String description,

        @NotNull(message = "Damage category is required")
        DamageCategory damageCategory,

        @NotNull(message = "Type name is required")
        Type type,

        @NotNull(message = "Base power is required")
        @Min(0) @Max(250)
        Integer basePower,

        @NotNull(message = "Power points are required")
        @Min(1) @Max(32)
        Integer powerPoints,

        @NotNull(message = "Accuracy is required")
        @Min(1) @Max(100)
        Integer accuracy
) {
}
