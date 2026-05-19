package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.MoveMapper;
import com.example.pokemonproject.dto.request.move.CreateMoveRequest;
import com.example.pokemonproject.dto.request.move.UpdateMoveRequest;
import com.example.pokemonproject.dto.response.MoveResponse;
import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.exception.DuplicateResourceException;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.MoveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MoveService {

    private final MoveRepository moveRepository;

    public Move findMoveByName(String name) {
        return moveRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Move with name " + name + " doesn't exist."));
    }

    public List<MoveResponse> getAll() {
        List<Move> moves = moveRepository.findAll();
        return moves.stream().map(MoveMapper::mapToMoveResponse).toList();
    }

    public MoveResponse getByName(String name) {
        Move move = findMoveByName(name);
        return MoveMapper.mapToMoveResponse(move);
    }

    @Transactional
    public MoveResponse save(CreateMoveRequest request) {
        Move move = MoveMapper.mapToMove(request);

        if (moveRepository.findByNameIgnoreCase(move.getName()).isPresent()) {
            throw new DuplicateResourceException("Move with name " + move.getName() + " already exists");
        }

        Move saved = moveRepository.save(move);

        return MoveMapper.mapToMoveResponse(saved);
    }

    @Transactional
    public MoveResponse update(String moveName, UpdateMoveRequest request) {
        Move move = findMoveByName(moveName);

        if (request.name() != null && !request.name().equalsIgnoreCase(move.getName())) {
            if (moveRepository.findByNameIgnoreCase(request.name()).isPresent()) {
                throw new DuplicateResourceException("Move with name " + request.name() + " already exists.");
            }
            move.setName(request.name());
        }

        if (request.description() != null) {
            move.setDescription(request.description());
        }

        if (request.damageCategory() != null) {
            move.setDamageCategory(request.damageCategory());
        }

        if (request.type() != null) {
            move.setType(request.type());
        }

        if (request.basePower() != null) {
            move.setBasePower(request.basePower());
        }

        if (request.powerPoints() != null) {
            move.setPowerPoints(request.powerPoints());
        }

        if (request.accuracy() != null) {
            move.setAccuracy(request.accuracy());
        }

        Move saved = moveRepository.save(move);

        return MoveMapper.mapToMoveResponse(saved);
    }

    @Transactional
    public String delete(String name) {
        moveRepository.delete(findMoveByName(name));
        return "Move deleted successfully!";
    }
}
