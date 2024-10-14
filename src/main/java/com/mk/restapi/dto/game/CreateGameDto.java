package com.mk.restapi.dto.game;

import jakarta.validation.constraints.NotNull;

public class CreateGameDto {
    @NotNull(message = "White player ID should not be null")
    private Long whitePlayerId;
    @NotNull(message = "Black player ID should not be null")
    private Long blackPlayerId;

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
}