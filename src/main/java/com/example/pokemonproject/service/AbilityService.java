package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.AbilityMapper;
import com.example.pokemonproject.dto.request.CreateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.entity.Ability;
import com.example.pokemonproject.exception.DuplicateResourceException;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.AbilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbilityService {

    private AbilityRepository abilityRepository;

    public List<AbilityResponse> getAllAbilities() {
        List<Ability> abilities = abilityRepository.findAll();
        return abilities.stream().map(AbilityMapper::mapToAbilityResponse).toList();
    }

    public AbilityResponse getAbilityByName(String name) {
        Ability ability = abilityRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Ability with name " + name + " doesn't exist."));
        return AbilityMapper.mapToAbilityResponse(ability);
    }

    public AbilityResponse saveAbility(CreateAbilityRequest abilityRequest) {
        Ability ability = AbilityMapper.mapToAbility(abilityRequest);

        if (abilityRepository.findByNameIgnoreCase(ability.getName()).isPresent()) {
            throw new DuplicateResourceException("Ability with name " + ability.getName() + " already exists");
        }

        abilityRepository.save(ability);
        return AbilityMapper.mapToAbilityResponse(ability);
    }
}
