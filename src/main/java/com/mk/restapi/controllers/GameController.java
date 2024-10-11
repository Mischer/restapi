package com.mk.restapi.controllers;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Move;
import com.mk.restapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/game/{gameId}/move")
    @SendTo("/topic/game/{gameId}")
    public Move makeMove(@DestinationVariable Long gameId, MoveDto moveDto) {
        Move move = gameService.processMove(gameId, moveDto);
        return move;
    }
}