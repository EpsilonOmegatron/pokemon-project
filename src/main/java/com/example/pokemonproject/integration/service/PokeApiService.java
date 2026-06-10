package com.example.pokemonproject.integration.service;

import com.example.pokemonproject.dto.mapper.PokemonMapper;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.exception.ApiException;
import com.example.pokemonproject.integration.dto.ApiSpeciesResponse;
import com.example.pokemonproject.integration.dto.PokeApiMapper;
import com.example.pokemonproject.integration.dto.ApiPokemonResponse;
import com.example.pokemonproject.repository.PokemonRepository;
import com.example.pokemonproject.common.utils.SlugUtils;
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
        String sanitizedName = SlugUtils.toSlug(name);

        Pokemon pokemon = pokeApiMapper.mapPokeApiResponse(getExternalPokemon(sanitizedName));

        pokemon.setName(getExternalEnglishName(sanitizedName));

        return pokemonMapper.mapToPokemonResponse(pokemonRepository.save(pokemon));
    }

    public String getExternalEnglishName(String name) {
        ApiSpeciesResponse apiSpeciesResponse = pokeApiRestClient.get()
                .uri("/pokemon-species/{name}", name.toLowerCase())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ApiException("PokeAPI couldn't find: " + name);
                })
                .body(ApiSpeciesResponse.class);

        return apiSpeciesResponse.names().stream()
                .filter(n -> "en".equals(n.language().name()))
                .map(ApiSpeciesResponse.NameSlot::name)
                .findFirst()
                .orElseThrow(() -> new ApiException("Couldn't find English name for: " + name));
    }

    public ApiPokemonResponse getExternalPokemon(String name) {
        return pokeApiRestClient.get()
                .uri("/pokemon/{name}", name.toLowerCase())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ApiException("PokeAPI couldn't find: " + name);
                })
                .body(ApiPokemonResponse.class);
    }
}
