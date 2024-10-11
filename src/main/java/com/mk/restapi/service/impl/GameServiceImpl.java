package com.mk.restapi.service.impl;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import com.mk.restapi.entity.User;
import com.mk.restapi.repository.MoveRepository;
import com.mk.restapi.repository.GameRepository;
import com.mk.restapi.exception.GameNotFoundException;
import com.mk.restapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MoveRepository moveRepository;

    @Override
    public Move processMove(Long gameId, MoveDto moveDto) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("The game not found gameId:" + gameId));

        // Validate and process move
        // Update FEN
        String newFen = updateFen(game.getCurrentFen(), moveDto.getPgnNotation());

        Move move = new Move();
        move.setGame(game);
        move.setPgnNotation(moveDto.getPgnNotation());
        move.setFen(newFen);
        move.setTimestamp(LocalDateTime.now());
        move.setMoveNumber(game.getMoves().size() + 1);

        moveRepository.save(move);

        game.setCurrentFen(newFen);
        gameRepository.save(game);

        return move;
    }

    @Override
    public Game createGame(User whitePlayer, User blackPlayer) {
        return new Game();
    }

    @Override
    public Optional<Game> findById(Long gameId) {
        return gameRepository.findById(gameId);
    }

    private String updateFen(String currentFen, String pgnNotation) {
        // Логика обновления FEN на основе текущего состояния и нового хода
        // Можно использовать шахматные библиотеки для этого
        return "";
    }
}
