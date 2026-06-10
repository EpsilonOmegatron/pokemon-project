package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.pokemon.CreatePokemonRequest;
import com.example.pokemonproject.dto.request.pokemon.UpdatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.common.utils.TypeChart;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", imports = {TypeChart.class})
public interface PokemonMapper {

    @Mapping(target = "abilities",
            expression = "java(pokemon.getAbilities() == null ? java.util.Collections.emptySet() : pokemon.getAbilities().stream().map(a -> a.getName()).collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "moves",
            expression = "java(pokemon.getMoves() == null ? java.util.Collections.emptySet() : pokemon.getMoves().stream().map(m -> m.getName()).collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "typeDefenses",
            expression = "java(TypeChart.calculateMultipliers(pokemon.getTypes()))")
    @Mapping(target = "evolvesFrom", source = "evolvesFrom.name")
    PokemonResponse mapToPokemonResponse(Pokemon pokemon);

    @Mapping(target = "evolvesFrom", ignore = true)
    Pokemon mapToPokemon(CreatePokemonRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "evolvesFrom", ignore = true)
    void updateFromRequest(UpdatePokemonRequest request, @MappingTarget Pokemon pokemon);
}