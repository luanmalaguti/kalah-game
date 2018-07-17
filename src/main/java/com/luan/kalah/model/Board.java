package com.luan.kalah.model;

import com.luan.kalah.constant.GameConfig;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Representation of the game board model
 */
public class Board {

    private Map<Integer, Integer> houses = new HashMap<>();

    public Board() {
        fillBoard();
    }

    /**
     * Fill the board houses with the number of stones defined at @{@link GameConfig#STONES_PER_HOUSE}
     */
    private void fillBoard() {
        for (int i = 1; i <= GameConfig.NUMBER_OF_HOUSES; i++) {
           houses.put(i, isKalahHouse(i) ? 0 : GameConfig.STONES_PER_HOUSE);
        }
    }

    /**
     * Indicates if a given hous is a Kalah house - based in the @{@link GameConfig} configurations
     */
    public boolean isKalahHouse(int i) {
        return i == GameConfig.KALAH_PLAYER_1  || i == GameConfig.KALAH_PLAYER_2;
    }

    public Map<Integer, Integer> getHouses() {
        return houses;
    }

    public void setHouses(Map<Integer, Integer> houses) {
        this.houses = houses;
    }

    @Override
    public String toString() {
        return new Gson().toJson(houses);
    }
}
