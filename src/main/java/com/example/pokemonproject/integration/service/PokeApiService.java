package com.example.pokemonproject.integration.service;

import com.example.pokemonproject.dto.mapper.PokemonMapper;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.integration.dto.PokeApiMapper;
import com.example.pokemonproject.integration.dto.PokeApiResponse;
import com.example.pokemonproject.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class PokeApiService {

    private final RestClient pokeApiRestClient;
    private final PokemonRepository pokemonRepository;
    private final PokemonMapper pokemonMapper;
    private final PokeApiMapper pokeApiMapper;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public PokemonResponse saveExternalPokemon(String name) {
        Pokemon pokemon = pokeApiMapper.mapPokeApiResponse(getExternalPokemon(name));
        return pokemonMapper.mapToPokemonResponse(pokemonRepository.save(pokemon));
    }

    public PokeApiResponse getExternalPokemon(String name) {
        return pokeApiRestClient.get()
                .uri("/pokemon/{name}", name.toLowerCase())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ResourceNotFoundException("PokeAPI couldn't find: " + name);
                })
                .body(PokeApiResponse.class);
    }
}
