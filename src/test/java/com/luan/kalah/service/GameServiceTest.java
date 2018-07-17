package com.luan.kalah.service;

import com.luan.kalah.model.Game;
import com.luan.kalah.model.GameResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired MoveService moveService;
    @Value("${server.port}")
    private String serverPort;

    @DisplayName("Create a game checking the response attributes")
    @Test
    public void testCreateGame(){
        gameService.resetGame();
        GameResponse gameResponse = gameService.createGame();
        Assert.assertNotNull(gameResponse);
        Assert.assertEquals(1L,gameResponse.getId());
        Assert.assertEquals("http://localhost:"+serverPort+"/games/1",gameResponse.getUri());
    }

    @DisplayName("Check the game url")
    @Test
    public void tesFormatUri(){
        String uri = gameService.formatUri(2L);
        Assert.assertNotNull(uri.isEmpty());
        Assert.assertFalse(uri.isEmpty());
        Assert.assertEquals("http://localhost:"+serverPort+"/games/2",uri);
    }

    @DisplayName("Create sequential and unique identifiers")
    @Test
    public void testNextId(){
        gameService.resetGame();
        Assert.assertEquals(new Long(1), gameService.nextId());
        Assert.assertEquals(new Long(2), gameService.nextId());
        Assert.assertEquals(new Long(3), gameService.nextId());
        Assert.assertEquals(new Long(4), gameService.nextId());
        Assert.assertEquals(new Long(5), gameService.nextId());
    }

    @DisplayName("Game has finished after side 1 houses are empty")
    @Test
    public void testGameHasFinished(){
        Game game = new Game(1L);
        game.getBoard().getHouses().put(1,0);
        game.getBoard().getHouses().put(2,0);
        game.getBoard().getHouses().put(3,0);
        game.getBoard().getHouses().put(4,0);
        game.getBoard().getHouses().put(5,0);
        game.getBoard().getHouses().put(6,0);
        Assert.assertTrue(gameService.hasGameFinished(game));
    }

    @DisplayName("Game has not been finished since both sides have stones")
    @Test
    public void testGameHasNotFinished(){
        Game game = new Game(1L);
        Assert.assertFalse(gameService.hasGameFinished(game));
    }

}