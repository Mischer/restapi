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
            // Создаем объект доски на основе текущего состояния FEN
            Board board = new Board();
            board.loadFromFen(game.getCurrentFen());

            // Создаем объект Move на основе PGN-нотации
            Move move = new Move(moveDto.getPgnNotation(), board.getSideToMove());

            // Проверяем, является ли ход легальным
            boolean isLegal = board.legalMoves().contains(move);

            return isLegal;
        } catch (MoveException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}