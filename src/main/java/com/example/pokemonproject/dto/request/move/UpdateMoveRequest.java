package com.example.pokemonproject.dto.request.move;

import com.example.pokemonproject.enums.DamageCategory;
import com.example.pokemonproject.enums.Type;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateMoveRequest(

        @Size(max = 50, message = "Move name must be <= 50 characters")
        String name,

        @Size(max = 50, message = "Move description must be <= 50 characters")
        String description,

        DamageCategory damageCategory,
        Type type,

        @Min(1) @Max(250)
        Integer basePower,

        @Min(1) @Max(32)
        Integer powerPoints,

        @Min(1) @Max(100)
        Integer accuracy
) {
}
