package com.luan.kalah.exception;

/**
 * Custom @{@link Exception} for Invalid houses (pits)
 */
public class InvalidHouseException extends GameException {

    public InvalidHouseException(String message) {
        super(message);
    }
}
