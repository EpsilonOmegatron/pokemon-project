package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.PokemonMapper;
import com.example.pokemonproject.dto.request.pokemon.AddPokemonAbilityRequest;
import com.example.pokemonproject.dto.request.pokemon.AddPokemonMoveRequest;
import com.example.pokemonproject.dto.request.pokemon.CreatePokemonRequest;
import com.example.pokemonproject.dto.response.PokemonResponse;
import com.example.pokemonproject.entity.Pokemon;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.PokemonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final AbilityService abilityService;
    private final MoveService moveService;

    public Pokemon findPokemonByName(String name) {
        return pokemonRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon with name " + name + " doesn't exist."));
    }

    public List<PokemonResponse> getAll() {
        List<Pokemon> pokemon = pokemonRepository.findAll();
        return pokemon.stream().map(PokemonMapper::mapToPokemonResponse).toList();
    }

    public PokemonResponse getByName(String name) {
        Pokemon pokemon = findPokemonByName(name);
        return PokemonMapper.mapToPokemonResponse(pokemon);
    }


    @Transactional
    public PokemonResponse save(CreatePokemonRequest request) {

        Pokemon pre_evo = null;

        if (request.evolvesFrom() != null && !request.evolvesFrom().isBlank()) {
            pre_evo = pokemonRepository.findByNameIgnoreCase(request.evolvesFrom())
                    .orElseThrow(() -> new ResourceNotFoundException("Pre-evo pokemon with name " + request.evolvesFrom() + " does not exist."));
        }
        Pokemon pokemon = PokemonMapper.mapToPokemon(request, pre_evo);

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }

    @Transactional
    public PokemonResponse addMoves(AddPokemonMoveRequest request) {
        Pokemon pokemon = findPokemonByName(request.pokemon());

        for (String moveName : request.moves()) {
            pokemon.getMoves().add(moveService.findMoveByName(moveName));
        }

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }

    @Transactional
    public PokemonResponse addAbilities(AddPokemonAbilityRequest request) {
        Pokemon pokemon = findPokemonByName(request.pokemon());

        for (String abilityName : request.abilities()) {
            pokemon.getAbilities().add(abilityService.findAbilityByName(abilityName));
        }

        Pokemon saved = pokemonRepository.save(pokemon);

        return PokemonMapper.mapToPokemonResponse(saved);
    }

//    @Transactional
//    public PokemonResponse update(UpdatePokemonRequest){
//        return
//    }

    @Transactional
    public String delete(String name) {
        Pokemon pokemon = findPokemonByName(name);

        pokemonRepository.delete(pokemon);

        return "Pokemon deleted successfully";
    }
}
