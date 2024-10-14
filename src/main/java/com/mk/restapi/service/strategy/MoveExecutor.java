package com.mk.restapi.service.strategy;

import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import com.mk.restapi.service.strategy.MoveStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MoveExecutor {

    @Autowired
    private Map<String, MoveStrategy> strategies;

    public void executeMove(String strategyName, Move move, Game game) {
        MoveStrategy strategy = strategies.get(strategyName);
        if (strategy != null) {
            strategy.execute(move, game);
        } else {
            throw new IllegalArgumentException("No strategy found for: " + strategyName);
        }
    }
}
