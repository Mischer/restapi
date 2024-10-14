package com.mk.restapi.mapper;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Move;
public interface MoveMapper {

    MoveDto toDto(Move move);

    Move toEntity(MoveDto moveDto);
}