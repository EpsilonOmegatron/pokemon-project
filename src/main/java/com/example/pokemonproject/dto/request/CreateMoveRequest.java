package com.example.pokemonproject.dto.request;

import com.example.pokemonproject.enums.DamageCategory;
import com.example.pokemonproject.enums.Type;

public record CreateMoveRequest(
        String name,
        String description,
        DamageCategory damageCategory,
        Type type,
        Integer basePower,
        Integer powerPoints,
        Integer accuracy
) {
}
