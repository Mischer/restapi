package com.mk.restapi.mapper;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Move;
import com.mk.restapi.mapper.MoveMapper;

public class MoveMapperImpl implements MoveMapper {

    @Override
    public MoveDto toDto(Move move) {
        return new MoveDto();
    }

    @Override
    public Move toEntity(MoveDto moveDto) {
        return new Move();
    }
}
