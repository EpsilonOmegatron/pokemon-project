package com.example.pokemonproject.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiSpeciesResponse(
        List<NameSlot> names
) {

    public record NameSlot(
            Language language,
            String name
    ) {
    }

    public record Language(
            String name
    ) {
    }
}
