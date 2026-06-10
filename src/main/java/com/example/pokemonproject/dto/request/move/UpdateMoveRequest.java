package com.example.pokemonproject.dto.request.move;

import com.example.pokemonproject.common.enums.DamageCategory;
import com.example.pokemonproject.common.enums.Type;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record UpdateMoveRequest(

        @Size(min = 5, max = 50, message = "{validation.range}")
        String name,

        @Size(min = 10, max = 100, message = "{validation.range}")
        String description,

        DamageCategory damageCategory,
        Type type,

        @Range(min = 0, max = 250, message = "{validation.range}")
        Integer basePower,

        @Range(min = 1, max = 64, message = "{validation.range}")
        Integer powerPoints,

        @Range(min = 1, max = 100, message = "{validation.range}")
        Integer accuracy
) {
}
