package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.CreateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.entity.Ability;

public class AbilityMapper {

    public static AbilityResponse mapToAbilityResponse(Ability ability) {
        return new AbilityResponse(ability.getId(), ability.getName(), ability.getDescription());
    }

    public static Ability mapToAbility(CreateAbilityRequest abilityRequest) {
        Ability ability = new Ability();
        ability.setName(abilityRequest.name());
        ability.setDescription(abilityRequest.description());
        return ability;
    }
}
