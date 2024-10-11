package com.mk.restapi.service.strategy;

import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import com.mk.restapi.service.strategy.MoveStrategy;
import org.springframework.stereotype.Component;

@Component("сastlingMoveStrategy")
public class CastlingMoveStrategy implements MoveStrategy {

    @Override
    public void execute(Move move, Game game) {
        // TODO process move logic
    }
}