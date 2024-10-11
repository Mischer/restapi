package com.mk.restapi.service.impl;

import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import com.mk.restapi.repository.MoveRepository;
import com.mk.restapi.service.MoveService;
import com.mk.restapi.util.FenUtil;
import com.mk.restapi.validator.MoveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoveServiceImpl implements MoveService {

    @Autowired
    private MoveRepository moveRepository;

    @Autowired
    private MoveValidator moveValidator;

    @Override
    public Move createMove(Game game, MoveDto moveDto) {
        // Валидация хода
        if (!validateMove(moveDto, game)) {
            throw new IllegalArgumentException("Invalid move");
        }

        String newFen = FenUtil.updateFen(game.getCurrentFen(), moveDto.getPgnNotation());

        Move move = new Move();
        move.setGame(game);
        move.setPgnNotation(moveDto.getPgnNotation());
        move.setFen(newFen);
        move.setMoveNumber(game.getMoves().size() + 1);
        move.setTimestamp(java.time.LocalDateTime.now());

        return moveRepository.save(move);
    }

    @Override
    public List<Move> getMovesByGame(Game game) {
        return moveRepository.findByGameOrderByMoveNumberAsc(game);
    }

    @Override
    public Optional<Move> findById(Long moveId) {
        return moveRepository.findById(moveId);
    }

    @Override
    public boolean validateMove(MoveDto moveDto, Game game) {
        return moveValidator.isValid(moveDto, game);
    }

    @Override
    public Game undoLastMove(Game game) {
        List<Move> moves = getMovesByGame(game);
        if (moves.isEmpty()) {
            throw new IllegalStateException("No moves to undo");
        }

        Move lastMove = moves.get(moves.size() - 1);
        moveRepository.delete(lastMove);

        String previousFen = moves.size() > 1 ? moves.get(moves.size() - 2).getFen() : FenUtil.getDefaultFen();
        game.setCurrentFen(previousFen);

        //TODO Save the current game state to DB

        return game;
    }
}
