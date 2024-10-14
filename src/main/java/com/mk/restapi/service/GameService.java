package com.mk.restapi.service;

import com.mk.restapi.dto.game.CreateGameDto;
import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;

import java.util.Optional;

public interface GameService {
    Move processMove(Long gameId, MoveDto moveDto);

    Game createGame(CreateGameDto createGameDto);

    Optional<Game> findById(Long gameId);
}
