package com.luan.kalah.validator;

import com.luan.kalah.constant.GameConfig;

import java.util.function.Function;

/**
 * General rules to be checked on every move
 */
public interface RulesValidator extends Function<Integer, Boolean> {

    static RulesValidator playerHasFinished(){
       return i -> i == 0;
    }

    static RulesValidator isKalahHouse(){
        return i -> i == GameConfig.KALAH_PLAYER_1 ||  i == GameConfig.KALAH_PLAYER_2;
    }

    static RulesValidator isInvalidHouse(){
        return i -> i > GameConfig.NUMBER_OF_HOUSES || i < 1;
    }

    static RulesValidator isEmptyHouse(){
        return stones -> stones == GameConfig.EMPTY_HOUSE;
    }
}
