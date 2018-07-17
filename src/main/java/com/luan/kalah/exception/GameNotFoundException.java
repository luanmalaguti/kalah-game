package com.luan.kalah.exception;

/**
 * Custom @{@link Exception} for Invalid games
 */
public class GameNotFoundException extends GameException {

    public GameNotFoundException(String message) {
        super(message);
    }
}
