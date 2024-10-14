package com.mk.restapi.service.impl;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.dto.game.CreateGameDto;
import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.GameStatus;
import com.mk.restapi.entity.Move;
import com.mk.restapi.entity.User;
import com.mk.restapi.exception.EntityNotFoundException;
import com.mk.restapi.repository.MoveRepository;
import com.mk.restapi.repository.GameRepository;
import com.mk.restapi.exception.GameNotFoundException;
import com.mk.restapi.repository.UserRepository;
import com.mk.restapi.service.GameService;
import com.mk.restapi.util.FenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final MoveRepository moveRepository;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository,MoveRepository moveRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.moveRepository = moveRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Game createGame(CreateGameDto createGameDto) {
        User whitePlayer = userRepository.findById(createGameDto.getWhitePlayerId())
                .orElseThrow(() -> new EntityNotFoundException("White player not found. ID " + createGameDto.getWhitePlayerId()));

        User blackPlayer = userRepository.findById(createGameDto.getBlackPlayerId())
                .orElseThrow(() -> new EntityNotFoundException("Black player not found. ID " + createGameDto.getBlackPlayerId()));

        if (whitePlayer.getId().equals(blackPlayer.getId())) {
            throw new IllegalArgumentException("The white and black players should be the different users");
        }

        Game game = new Game();
        game.setWhitePlayer(whitePlayer);
        game.setBlackPlayer(blackPlayer);
        game.setStatus(GameStatus.NEW);
        game.setCurrentFen(FenUtil.getDefaultFen());

        return gameRepository.save(game);
    }

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
    public Optional<Game> findById(Long gameId) {
        return gameRepository.findById(gameId);
    }

    private String updateFen(String currentFen, String pgnNotation) {
        // Логика обновления FEN на основе текущего состояния и нового хода
        // Можно использовать шахматные библиотеки для этого
        return "";
    }
}
