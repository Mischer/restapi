package com.mk.restapi.service;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import com.mk.restapi.entity.User;

import java.util.Optional;

import java.util.List;

public interface MoveService {

    Move createMove(Game game, MoveDto moveDto);

    List<Move> getMovesByGame(Game game);

    Optional<Move> findById(Long moveId);

    boolean validateMove(MoveDto moveDto, Game game);

    Game undoLastMove(Game game);
}