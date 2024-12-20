package com.mk.restapi.repository;

import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveRepository extends JpaRepository<Move, Long> {
    List<Move> findByGameOrderByMoveNumberAsc(Game game);
}
