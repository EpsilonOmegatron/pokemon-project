package com.example.pokemonproject.dto.request.move;

import com.example.pokemonproject.common.enums.DamageCategory;
import com.example.pokemonproject.common.enums.Type;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public record CreateMoveRequest(

        @NotBlank(message = "{validation.required}")
        @Size(min = 5, max = 50, message = "{validation.range}")
        String name,

        @NotBlank(message = "{validation.required}")
        @Size(min = 5, max = 100, message = "{validation.range}")
        String description,

        @NotNull(message = "{validation.required}")
        DamageCategory damageCategory,

        @NotNull(message = "{validation.required}")
        Type type,

        @NotNull(message = "{validation.required}")
        @Range(min = 0, max = 250, message = "{validation.range}")
        Integer basePower,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 64, message = "{validation.range}")
        Integer powerPoints,

        @NotNull(message = "{validation.required}")
        @Range(min = 1, max = 100, message = "{validation.range}")
        Integer accuracy
) {
}
