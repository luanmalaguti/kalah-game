package com.luan.kalah.exception;

/**
 * General @{@link RuntimeException} to be extended for the custom app exceptions
 */
public class GameException extends RuntimeException{

    public GameException(String message) {
        super(message);
    }

}
