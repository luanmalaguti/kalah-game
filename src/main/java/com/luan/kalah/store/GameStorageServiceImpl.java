package com.luan.kalah.store;

import com.luan.kalah.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of @{@link IGameStorage} using the In Memory storage @{@link GameStorage}
 */
@Service
public class GameStorageServiceImpl implements IGameStorage{

    @Autowired
    private GameStorage gameStorage;

    @Override
    public void save(Game game){
        gameStorage.save(game);
    }

    @Override
    public Game load(Long gameId) {
        return gameStorage.load(gameId);
    }

    @Override
    public void resetGame() {
        gameStorage.clearStorge();
    }

}
