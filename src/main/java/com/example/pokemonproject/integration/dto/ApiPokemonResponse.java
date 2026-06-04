package com.example.pokemonproject.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiPokemonResponse(
        String name,
        List<TypeSlot> types,
        List<AbilitySlot> abilities,
        List<StatSlot> stats
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AbilitySlot(
            Ability ability,

            @JsonProperty("is_hidden")
            boolean isHidden

    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Ability(String name) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record StatSlot(
            @JsonProperty("base_stat")
            Integer baseStat,
            Stat stat
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Stat(String name) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TypeSlot(Type type) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Type(String name) {
    }
}