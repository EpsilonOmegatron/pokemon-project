package com.example.pokemonproject.dto.mapper;

import com.example.pokemonproject.dto.request.move.CreateMoveRequest;
import com.example.pokemonproject.dto.request.move.UpdateMoveRequest;
import com.example.pokemonproject.dto.response.MoveResponse;
import com.example.pokemonproject.entity.Move;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MoveMapper {

    MoveResponse mapToMoveResponse(Move move);

    Move mapToMove(CreateMoveRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMoveFromRequest(UpdateMoveRequest request, @MappingTarget Move move);
}
