package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.MoveMapper;
import com.example.pokemonproject.dto.request.move.CreateMoveRequest;
import com.example.pokemonproject.dto.request.move.UpdateMoveRequest;
import com.example.pokemonproject.dto.response.MoveResponse;
import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.enums.DamageCategory;
import com.example.pokemonproject.enums.Type;
import com.example.pokemonproject.exception.DuplicateResourceException;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.MoveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MoveService {

    private final MoveRepository moveRepository;
    private final MoveMapper moveMapper;

    public Move findMoveByName(String name) {
        return moveRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Move with name " + name + " doesn't exist."));
    }

    public List<MoveResponse> getAll() {
        List<Move> moves = moveRepository.findAll();
        return moves.stream().map(moveMapper::mapToMoveResponse).toList();
    }

    public MoveResponse getByName(String name) {
        Move move = findMoveByName(name);
        return moveMapper.mapToMoveResponse(move);
    }

    public List<MoveResponse> getAllByDamageCategory(String category) {
        DamageCategory parsedCategory;
        try {
            parsedCategory = DamageCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + category);
        }

        List<Move> moves = moveRepository.findAllByDamageCategory(parsedCategory);
        return moves.stream().map(moveMapper::mapToMoveResponse).toList();
    }

    public List<MoveResponse> getAllByType(String type) {
        Type parsedType;
        try {
            parsedType = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid type " + type);
        }

        List<Move> pokemon = moveRepository.findByTypeContaining(parsedType);
        return pokemon.stream().map(moveMapper::mapToMoveResponse).toList();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public MoveResponse save(CreateMoveRequest request) {
        Move move = moveMapper.mapToMove(request);

        if (moveRepository.findByNameIgnoreCase(move.getName()).isPresent()) {
            throw new DuplicateResourceException("Move with name " + move.getName() + " already exists");
        }

        Move saved = moveRepository.save(move);

        return moveMapper.mapToMoveResponse(saved);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public MoveResponse update(String moveName, UpdateMoveRequest request) {
        Move move = findMoveByName(moveName);

        if (request.name() != null && !request.name().equalsIgnoreCase(move.getName())) {
            if (moveRepository.findByNameIgnoreCase(request.name()).isPresent()) {
                throw new DuplicateResourceException("Move with name " + request.name() + " already exists.");
            }
        }

        moveMapper.updateMoveFromRequest(request, move);

        return moveMapper.mapToMoveResponse(move);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(String name) {
        moveRepository.delete(findMoveByName(name));
        return "Move deleted successfully!";
    }
}
