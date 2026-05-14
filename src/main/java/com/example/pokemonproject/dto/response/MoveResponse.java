package com.example.pokemonproject.dto.response;

import com.example.pokemonproject.enums.DamageCategory;
import com.example.pokemonproject.enums.Type;

public record MoveResponse(
        Integer id,
        String name,
        String description,
        DamageCategory damageCategory,
        Type type,
        Integer basePower,
        Integer powerPoints,
        Integer accuracy
) {
}
