package com.mk.restapi.service;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import com.mk.restapi.entity.User;

import java.util.Optional;

public interface GameService {
    Move processMove(Long gameId, MoveDto moveDto);

    Game createGame(User whitePlayer, User blackPlayer);

    Optional<Game> findById(Long gameId);
}
