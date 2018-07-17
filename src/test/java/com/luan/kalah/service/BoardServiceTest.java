package com.luan.kalah.service;

import com.luan.kalah.constant.GameConfig;
import com.luan.kalah.model.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @DisplayName("Get winner - scenario Player 1 (kalah 7) won")
    @Test
    public void testGetWinnerPlayer1() {
        Game game = new Game(1L);
        game.getBoard().getHouses().put(GameConfig.KALAH_PLAYER_1, 64);
        game.getBoard().getHouses().put(GameConfig.KALAH_PLAYER_2, 8);

        Assert.assertEquals("Player Kalah 7 - 64 x 8", boardService.getWinner(game));
    }

    @DisplayName("Get winner - scenario Player 1 (kalah 14) won")
    @Test
    public void testGetWinnerPlayer2() {
        Game game = new Game(1L);
        game.getBoard().getHouses().put(GameConfig.KALAH_PLAYER_1, 8);
        game.getBoard().getHouses().put(GameConfig.KALAH_PLAYER_2, 64);

        Assert.assertEquals("Player Kalah 14 - 64 x 8", boardService.getWinner(game));
    }

    @DisplayName("Get some of stones map range")
    @Test
    public void testGetSumOfStones() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 20);
        map.put(2, 0);
        map.put(3, 10);
        map.put(4, 4);
        map.put(5, 6);
        map.put(6, 0);

        int sum = boardService.getSumOfStones(map);
        Assert.assertEquals(40, sum);
    }

    @DisplayName("Get the right sub map for the player 1 - houses 1 to 6")
    @Test
    public void testGetPlayer1Board() {
        Game game = new Game(1L);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 6);
        map.put(2, 6);
        map.put(3, 6);
        map.put(4, 6);
        map.put(5, 6);
        map.put(6, 6);

        Map mapPlayer1 = boardService.getPlayer1Board(game);
        Assert.assertEquals(map, mapPlayer1);
    }

    @DisplayName("Get the right sub map for the player 2 - houses 8 to 13")
    @Test
    public void testGetPlayer2Board() {
        Game game = new Game(1L);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(8, 6);
        map.put(9, 6);
        map.put(10, 6);
        map.put(11, 6);
        map.put(12, 6);
        map.put(13, 6);

        Map mapPlayer2 = boardService.getPlayer2Board(game);
        Assert.assertEquals(map, mapPlayer2);
    }



}