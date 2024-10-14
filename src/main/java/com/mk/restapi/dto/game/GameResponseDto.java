package com.mk.restapi.dto.game;

import com.mk.restapi.entity.Game;
import com.mk.restapi.entity.GameStatus;

import java.time.LocalDateTime;

public class GameResponseDto {
    private Long id;
    private Long whitePlayerId;
    private Long blackPlayerId;
    private GameStatus status;
    private String currentFen;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GameResponseDto(Game game) {
        this.id = game.getId();
        this.whitePlayerId = game.getWhitePlayer().getId();
        this.blackPlayerId = game.getBlackPlayer().getId();
        this.status = game.getStatus();
        this.currentFen = game.getCurrentFen();
        this.createdAt = game.getCreatedAt();
        this.updatedAt = game.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWhitePlayerId() {
        return whitePlayerId;
    }

    public void setWhitePlayerId(Long whitePlayerId) {
        this.whitePlayerId = whitePlayerId;
    }

    public Long getBlackPlayerId() {
        return blackPlayerId;
    }

    public void setBlackPlayerId(Long blackPlayerId) {
        this.blackPlayerId = blackPlayerId;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getCurrentFen() {
        return currentFen;
    }

    public void setCurrentFen(String currentFen) {
        this.currentFen = currentFen;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
