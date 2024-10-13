package com.mk.restapi.validator;


import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveException;
import com.mk.restapi.dto.MoveDto;
import com.mk.restapi.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class MoveValidatorImpl implements MoveValidator {
    @Override
    public boolean isValid(MoveDto moveDto, Game game) {
        try {
            // Create a board by current FEN
            Board board = new Board();
            board.loadFromFen(game.getCurrentFen());

            // Create a move by PGN notation
            Move move = new Move(moveDto.getPgnNotation(), board.getSideToMove());

            // check is the next move is legal
            boolean isLegal = board.legalMoves().contains(move);

            return isLegal;
        } catch (MoveException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}