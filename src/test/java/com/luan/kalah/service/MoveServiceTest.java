package com.luan.kalah.service;

import com.luan.kalah.constant.GameConfig;
import com.luan.kalah.exception.GameNotFoundException;
import com.luan.kalah.exception.InvalidHouseException;
import com.luan.kalah.model.StatusResponse;
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
public class MoveServiceTest {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveService moveService;

    @DisplayName("Move a invalid game")
    @Test(expected = GameNotFoundException.class)
    public void testInvalidGameMove(){
        moveService.move(2L,1);
    }

    @DisplayName("Move a valid game but kalah house")
    @Test(expected = InvalidHouseException.class)
    public void testMovePitKalahHouse(){
        gameService.resetGame();
        gameService.createGame();
        moveService.move(1L,GameConfig.KALAH_PLAYER_1);
        moveService.move(1L,GameConfig.KALAH_PLAYER_2);
    }

    @DisplayName("Move a valid game but invalid house < 1 or > 15 ")
    @Test(expected = InvalidHouseException.class)
    public void testMovePitInvalidHouse(){
        gameService.resetGame();
        gameService.createGame();
        moveService.move(1L,-1);
        moveService.move(1L,15);
    }

    @DisplayName("Move a valid game with a valid PitId")
    @Test
    public void testMoveSuccess(){
        gameService.resetGame();
        gameService.createGame();
        StatusResponse response = moveService.move(1L,1);

        Assert.assertNotNull(response);
        Assert.assertEquals(1L, response.getId());
        Assert.assertEquals("http://localhost:"+serverPort+"/games/1", response.getUri());
        String status = "{\"1\":0,\"2\":7,\"3\":7,\"4\":7,\"5\":7,\"6\":7,\"7\":1,\"8\":6,\"9\":6,\"10\":6,\"11\":6,\"12\":6,\"13\":6,\"14\":0}";
        Assert.assertEquals(status, response.getStatus());
    }

}