package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.CreateMoveRequest;
import com.example.pokemonproject.dto.response.MoveResponse;
import com.example.pokemonproject.entity.Move;

public class MoveMapper {
    public static MoveResponse mapToMoveResponse(Move move) {
        return new MoveResponse(
                move.getId(),
                move.getName(),
                move.getDescription(),
                move.getDamageCategory(),
                move.getType(),
                move.getBasePower(),
                move.getPowerPoints(),
                move.getAccuracy()
        );
    }

    public static Move mapToMove(CreateMoveRequest createMoveRequest) {
        Move move = new Move();
        move.setName(createMoveRequest.name());
        move.setDescription(createMoveRequest.description());
        move.setDamageCategory(createMoveRequest.damageCategory());
        move.setType(createMoveRequest.type());
        move.setBasePower(createMoveRequest.basePower());
        move.setPowerPoints(createMoveRequest.powerPoints());
        move.setAccuracy(createMoveRequest.accuracy());
        return move;
    }
}
