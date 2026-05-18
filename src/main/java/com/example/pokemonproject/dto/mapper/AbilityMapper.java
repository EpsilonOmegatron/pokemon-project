package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.ability.CreateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.entity.Ability;

public class AbilityMapper {

    public static AbilityResponse mapToAbilityResponse(Ability ability) {
        return new AbilityResponse(ability.getId(), ability.getName(), ability.getDescription());
    }

    public static Ability mapToAbility(CreateAbilityRequest request) {
        Ability ability = new Ability();
        ability.setName(request.name());
        ability.setDescription(request.description());
        return ability;
    }
}
