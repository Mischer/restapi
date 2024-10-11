package com.mk.restapi.repository;

import com.mk.restapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByStatus(String status);
}
