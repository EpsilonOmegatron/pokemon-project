package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.ability.CreateAbilityRequest;
import com.example.pokemonproject.dto.request.ability.UpdateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.entity.Ability;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AbilityMapper {

    AbilityResponse mapToAbilityResponse(Ability ability);

    Ability mapToAbility(CreateAbilityRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAbilityFromRequest(UpdateAbilityRequest request, @MappingTarget Ability ability);
}
