package com.luan.kalah.model;


import javax.validation.Valid;

/**
 * Representation of the game model
 */
@Valid
public class Game {
    private Long id;

    private Board board;

    public Game(Long id) {
        this.id = id;
        this.board = new Board();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
