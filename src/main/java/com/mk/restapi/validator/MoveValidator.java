package com.mk.restapi.validator;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Game;

public interface MoveValidator {

    boolean isValid(MoveDto moveDto, Game game);
}
