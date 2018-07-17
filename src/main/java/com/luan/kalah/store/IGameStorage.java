package com.luan.kalah.store;

import com.luan.kalah.model.Game;

/**
 * Defines the contracts that should be implemented by any Storage of @{@link Game}
 */
public interface IGameStorage {

    void save(Game game);

    Game load (Long gameId);

    void resetGame();
}
