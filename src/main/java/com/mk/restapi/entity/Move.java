package com.mk.restapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer moveNumber;

    private String pgnNotation;

    // Board position in FEN
    @Lob
    private String fen;

    // Player how make a move
    @ManyToOne
    @JoinColumn(name = "player_id")
    private User player;

    // The game related with this move
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(Integer moveNumber) {
        this.moveNumber = moveNumber;
    }

    public String getPgnNotation() {
        return pgnNotation;
    }

    public void setPgnNotation(String pgnNotation) {
        this.pgnNotation = pgnNotation;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
