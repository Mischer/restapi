package com.mk.restapi.service.strategy;

import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;

public interface MoveStrategy {

    void execute(Move move, Game game);
}