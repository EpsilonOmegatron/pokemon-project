package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.AbilityMapper;
import com.example.pokemonproject.dto.request.ability.CreateAbilityRequest;
import com.example.pokemonproject.dto.request.ability.UpdateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.entity.Ability;
import com.example.pokemonproject.exception.DuplicateResourceException;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.AbilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AbilityService {

    private final AbilityRepository abilityRepository;

    public Ability findAbilityByName(String name) {
        return abilityRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Ability with name " + name + " doesn't exist."));
    }

    public List<AbilityResponse> getAll() {
        List<Ability> abilities = abilityRepository.findAll();
        return abilities.stream().map(AbilityMapper::mapToAbilityResponse).toList();
    }

    public AbilityResponse getByName(String name) {
        Ability ability = findAbilityByName(name);
        return AbilityMapper.mapToAbilityResponse(ability);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AbilityResponse save(CreateAbilityRequest request) {
        Ability ability = AbilityMapper.mapToAbility(request);

        if (abilityRepository.findByNameIgnoreCase(ability.getName()).isPresent()) {
            throw new DuplicateResourceException("Ability with name " + ability.getName() + " already exists");
        }

        Ability saved = abilityRepository.save(ability);

        return AbilityMapper.mapToAbilityResponse(saved);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AbilityResponse update(String abilityName, UpdateAbilityRequest request) {
        Ability ability = findAbilityByName(abilityName);

        if (request.name() != null && !request.name().equals(ability.getName())) {
            if (abilityRepository.findByNameIgnoreCase(request.name()).isPresent()) {
                throw new DuplicateResourceException("Ability with name " + request.name() + " already exists.");
            }
            ability.setName(request.name());
        }

        if (request.description() != null) {
            ability.setDescription(request.description());
        }

        Ability saved = abilityRepository.save(ability);

        return AbilityMapper.mapToAbilityResponse(saved);
    }


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(String name) {
        abilityRepository.delete(findAbilityByName(name));
        return "Ability deleted successfully!";
    }
}
