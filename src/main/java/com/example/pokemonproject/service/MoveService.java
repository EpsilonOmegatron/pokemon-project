package com.example.pokemonproject.service;

import com.example.pokemonproject.dto.mapper.MoveMapper;
import com.example.pokemonproject.dto.request.CreateMoveRequest;
import com.example.pokemonproject.dto.response.MoveResponse;
import com.example.pokemonproject.entity.Move;
import com.example.pokemonproject.exception.DuplicateResourceException;
import com.example.pokemonproject.exception.ResourceNotFoundException;
import com.example.pokemonproject.repository.MoveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MoveService {

    private MoveRepository moveRepository;

    public List<MoveResponse> getAllMoves() {
        List<Move> moves = moveRepository.findAll();
        return moves.stream().map(MoveMapper::mapToMoveResponse).toList();
    }

    public MoveResponse getMoveByName(String name) {
        Move move = moveRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Move with name " + name + " doesn't exist."));
        return MoveMapper.mapToMoveResponse(move);
    }

    public MoveResponse saveMove(CreateMoveRequest createMoveRequest) {
        Move move = MoveMapper.mapToMove(createMoveRequest);

        if (moveRepository.findByNameIgnoreCase(move.getName()).isPresent()) {
            throw new DuplicateResourceException("Move with name " + move.getName() + " already exists");
        }

        moveRepository.save(move);
        return MoveMapper.mapToMoveResponse(move);
    }
}
