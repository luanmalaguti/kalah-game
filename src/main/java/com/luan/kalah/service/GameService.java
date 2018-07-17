package com.luan.kalah.service;

import com.luan.kalah.constant.GameConfig;
import com.luan.kalah.model.Game;
import com.luan.kalah.model.GameResponse;
import com.luan.kalah.store.IGameStorage;
import com.luan.kalah.validator.RulesValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameService {

    private static Logger log = LoggerFactory.getLogger(GameService.class);


    public static final int INITIAL_VALUE = 1;
    private AtomicLong gameId = new AtomicLong(INITIAL_VALUE);
    private IGameStorage iGameStorage;
    @Autowired
    private BoardService boardService;

    @Value("${server.port}")
    private String serverPort;
    @Value("${server.adress}")
    private String serverAdress;

    @Autowired
    public GameService(IGameStorage iGameStorage){
        this.iGameStorage = iGameStorage;
    }

    /**
     * Creates a new kalah game with a unique identifier and the board ready to play
     *
     * @return @{@link GameResponse}
     */
    public GameResponse createGame() {
        Game game = new Game(nextId());
        iGameStorage.save(game);
        return  new GameResponse(game.getId(), formatUri(game.getId()));
    }

    /**
     * Formats the uri for a Game
     *
     * Ex. <a href="http://localhost:8080/games/1"/>
     *
     * @param @{@link Game} id
     * @return formatted uri
     */
    public String formatUri(Long id) {
        return new StringBuilder("http://").
                append(serverAdress).
                append(":").
                append(serverPort).
                append("/games/").
                append(id).toString();
    }

    /**
     * Creates a unique sequential id for a @{@link Game}
     *
     * @return unique id
     */
    public Long nextId(){
        return gameId.getAndIncrement();
    }

    /**
     * Checks if the game has finished
     * It happens when one of the sides of the board has no remaining stones to play
     *
     * @param game
     */
    public boolean hasGameFinished(Game game) {
        Map<Integer, Integer> player1 = boardService.getPlayer1Board(game);
        Map<Integer, Integer> player2 = boardService.getPlayer2Board(game);
        int player1Stones = boardService.getSumOfStones(player1);
        int player2Stones = boardService.getSumOfStones(player2);
        boolean finished = (RulesValidator.playerHasFinished().apply(player1Stones) || RulesValidator.playerHasFinished().apply(player2Stones));
        if (finished){
            //move the remaining stones to the kalah house
            int remainingStones = player1Stones + player2Stones;
            boardService.joinRemainingStones(game, player1Stones > 0 ? GameConfig.KALAH_PLAYER_1 : GameConfig.KALAH_PLAYER_2, remainingStones);
        }
        return finished;
    }

    public void printGame(Game game){
        String print = game.getBoard().toString();
        log.info(print);
    }

    public void resetGame(){
        gameId = new AtomicLong(INITIAL_VALUE);
        iGameStorage.resetGame();
    }

}
