package com.luan.kalah.store;

import com.luan.kalah.model.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameStorageTest {

    @Autowired
    private GameStorage gameStorage;

    @DisplayName("Save single Game successful")
    @Test
    public void testSaveGame() {
        Assert.assertNull(gameStorage.load(1L));

        Game game = new Game(1L);
        gameStorage.save(game);

        Assert.assertNotNull(gameStorage.load(1L));
    }

    @DisplayName("Save single Game successful")
    @Test
    public void testSaveGameNull() {
        try {
            gameStorage.clearStorge();
            gameStorage.save(null);
            Assert.assertNull(gameStorage.load(1L));
        }catch (NullPointerException e){
            Assert.fail();
        }
    }

    @DisplayName("Try to load null Game")
    @Test
    public void testLoadGameNull() {
        try {
            Assert.assertNull(gameStorage.load(null));
        }catch (NullPointerException e){
            Assert.fail();
        }
    }

    @DisplayName("Try to load multiple games")
    @Test
    public void testLoadGames() {
        Game game1 = new Game(1L);
        Game game2 = new Game(2L);
        Game game3 = new Game(3L);
        gameStorage.save(game1);
        gameStorage.save(game2);
        gameStorage.save(game3);

        Assert.assertNotNull(game2);
        Assert.assertNotNull(game3);
        Assert.assertNotNull(game1);

        Assert.assertEquals(game2, gameStorage.load(2L));
        Assert.assertEquals(game3, gameStorage.load(3L));
        Assert.assertEquals(game1, gameStorage.load(1L));
    }

}