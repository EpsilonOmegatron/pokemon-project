package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.move.CreateMoveRequest;
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

    public static Move mapToMove(CreateMoveRequest request) {
        Move move = new Move();
        move.setName(request.name());
        move.setDescription(request.description());
        move.setDamageCategory(request.damageCategory());
        move.setType(request.type());
        move.setBasePower(request.basePower());
        move.setPowerPoints(request.powerPoints());
        move.setAccuracy(request.accuracy());
        return move;
    }
}
