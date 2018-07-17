package com.luan.kalah.validator;

import com.luan.kalah.model.Game;

import java.util.Objects;
import java.util.function.Function;

/**
 * General game rules
 */
public interface GameValidator extends Function<Game, Boolean> {

    static GameValidator gameExists() {
        return Objects::nonNull;
    }
}
