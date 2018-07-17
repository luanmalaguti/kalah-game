package com.luan.kalah.service;

import com.luan.kalah.constant.GameConfig;
import com.luan.kalah.exception.GameException;
import com.luan.kalah.model.Game;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BoardService {

    /**
     * Format and return the winner plus the score
     */
    public String getWinner(Game game){
        int kalah1 = game.getBoard().getHouses().get(GameConfig.KALAH_PLAYER_1);
        int kalah2 = game.getBoard().getHouses().get(GameConfig.KALAH_PLAYER_2);

        String winner = kalah1 > kalah2 ? GameConfig.PLAYER_1 : GameConfig.PLAYER_2;
        String result = kalah1 > kalah2 ? (kalah1 + " x " + kalah2) : (kalah2 + " x " + kalah1);
        return winner + " - " + result;
    }

    /**
     * Return the sum of the stones for a sub map representing one of the players
     */
    public Integer getSumOfStones(Map<Integer, Integer> map){
        return map.values().stream().mapToInt(Number::intValue).sum();
    }

    /**
     * Returns the player 1 sub map - houses 1 to 6
     */
    public Map<Integer, Integer> getPlayer1Board(Game game){
        return game.getBoard()
                .getHouses()
                .entrySet()
                .stream()
                .filter(map -> map.getKey() < GameConfig.KALAH_PLAYER_1)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Returns the player 1 sub map - houses 8 to 13
     */
    public Map<Integer, Integer> getPlayer2Board(Game game){
        return game.getBoard()
                .getHouses()
                .entrySet()
                .stream()
                .filter(map -> map.getKey() > GameConfig.KALAH_PLAYER_1 &&  map.getKey() < GameConfig.KALAH_PLAYER_2)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Joins the remaining stones with the stones of the given the kalah house
     *
     */
    public void joinRemainingStones(Game game, int kalahHouse, int remainingStones) {
        int kalahStones = game.getBoard().getHouses().get(kalahHouse);
        game.getBoard().getHouses().put(kalahHouse, kalahStones + remainingStones);
    }

    /**
     * Retrieves the number os stones for a given house
     */
    public Integer getHouseStones(int pitId, Map<Integer, Integer> houses) {
        try {
            return houses.get(pitId);
        }
        catch (NullPointerException e){
            throw new GameException("Invalid pit " + pitId);
        }
    }

    /**
     * Clears a given house setting the value to the default @{@link GameConfig#EMPTY_HOUSE}
     */
    public void clearHouse(int pitId, Map<Integer, Integer> houses) {
        houses.put(pitId, GameConfig.EMPTY_HOUSE);
    }
}
