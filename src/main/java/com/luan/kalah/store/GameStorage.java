package com.luan.kalah.store;

import com.luan.kalah.model.Game;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * In memory storage of @{@link Game}
 *
 * The game can store multiple games stored in a structure of @{@link Map}
 */
@Component
public class GameStorage {

    private Map<Long, Game> games = new TreeMap<>();

    public void save(Game game) {
        if (null != game){
            games.put(game.getId(), game);
        }
    }

    public Game load(Long gameId){
        if (null == gameId){
            return null;
        }
        return games.get(gameId);
    }

    public void clearStorge(){
        games = new TreeMap<>();
    }
}
