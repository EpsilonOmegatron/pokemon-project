package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.AbilityMapper;
import com.example.pokemonproject.dto.request.ability.CreateAbilityRequest;
import com.example.pokemonproject.dto.request.ability.UpdateAbilityRequest;
import com.example.pokemonproject.dto.response.AbilityResponse;
import com.example.pokemonproject.entity.Ability;
import com.example.pokemonproject.exception.DuplicateResourceException;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.AbilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AbilityService {

    private final AbilityRepository abilityRepository;
    private final AbilityMapper abilityMapper;

    public Ability findAbilityByName(String name) {
        return abilityRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Ability with name " + name + " doesn't exist."));
    }

    public List<AbilityResponse> getAll() {
        List<Ability> abilities = abilityRepository.findAll();
        return abilities.stream().map(abilityMapper::mapToAbilityResponse).toList();
    }

    public AbilityResponse getByName(String name) {
        Ability ability = findAbilityByName(name);
        return abilityMapper.mapToAbilityResponse(ability);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AbilityResponse save(CreateAbilityRequest request) {
        Ability ability = abilityMapper.mapToAbility(request);

        if (abilityRepository.findByNameIgnoreCase(ability.getName()).isPresent()) {
            throw new DuplicateResourceException("Ability with name " + ability.getName() + " already exists");
        }

        Ability saved = abilityRepository.save(ability);

        return abilityMapper.mapToAbilityResponse(saved);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AbilityResponse update(String abilityName, UpdateAbilityRequest request) {
        Ability ability = findAbilityByName(abilityName);

        if (request.name() != null && !request.name().equals(ability.getName())) {
            if (abilityRepository.findByNameIgnoreCase(request.name()).isPresent()) {
                throw new DuplicateResourceException("Ability with name " + request.name() + " already exists.");
            }
        }

        abilityMapper.updateAbilityFromRequest(request, ability);

        return abilityMapper.mapToAbilityResponse(ability);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(String name) {
        abilityRepository.delete(findAbilityByName(name));
        return "Ability deleted successfully!";
    }
}
