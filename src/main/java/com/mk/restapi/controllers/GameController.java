package com.mk.restapi.controllers;

import com.mk.restapi.dto.game.CreateGameDto;
import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.dto.game.GameResponseDto;
import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import com.mk.restapi.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/game/{gameId}/move")
    @SendTo("/topic/game/{gameId}")
    public Move makeMove(@DestinationVariable Long gameId, MoveDto moveDto) {
        Move move = gameService.processMove(gameId, moveDto);
        return move;
    }

    @PostMapping
    public ResponseEntity<GameResponseDto> createGame(@Valid @RequestBody CreateGameDto createGameDto) {
        Game game = gameService.createGame(createGameDto);
        GameResponseDto response = new GameResponseDto(game);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}