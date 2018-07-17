package com.luan.kalah.service;

import com.luan.kalah.constant.GameConfig;
import com.luan.kalah.exception.InvalidHouseException;
import com.luan.kalah.exception.GameException;
import com.luan.kalah.exception.GameNotFoundException;
import com.luan.kalah.model.Game;
import com.luan.kalah.model.StatusResponse;
import com.luan.kalah.store.IGameStorage;
import com.luan.kalah.validator.GameValidator;
import com.luan.kalah.validator.RulesValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class MoveService {

    private static Logger log = LoggerFactory.getLogger(MoveService.class);
    private IGameStorage iGameStorage;

    @Autowired
    private GameService gameService;
    @Autowired
    private BoardService boardService;

    @Autowired
    public MoveService(IGameStorage iGameStorage){
        this.iGameStorage = iGameStorage;
    }

    /**
     * Applies move to a given {@param gameId}
     *
     * @param gameId
     * @param pitId
     *
     * @throws GameException
     */
    public StatusResponse move(long gameId, int pitId) {
        if (RulesValidator.isInvalidHouse().apply(pitId) || RulesValidator.isKalahHouse().apply(pitId)){
            throw new InvalidHouseException("Invalid house "+ pitId);
        }
        Game game = iGameStorage.load(gameId);
        if(!GameValidator.gameExists().apply(game)) {
            throw new GameNotFoundException("No game found!");
        }

        log.info("Game {} loaded {}", gameId, game.getBoard());
        sow(game, pitId);
        iGameStorage.save(game);

        if(gameService.hasGameFinished(game)){
            String status = "Game has finished! " + boardService.getWinner(game);
            return new StatusResponse(gameId, gameService.formatUri(gameId), status);
        }
        return new StatusResponse(gameId, gameService.formatUri(gameId), game.getBoard().toString());
    }

    /**
     * Pull stones from a given house {@param pit} and sow them anti-clockwise
     *
     * @param game
     * @param pitId
     *
     * @throws InvalidHouseException
     */
    private Game sow(Game game, int pitId) {
        Map<Integer, Integer> houses = game.getBoard().getHouses();
        int houseStones = boardService.getHouseStones(pitId, houses);
        if (RulesValidator.isEmptyHouse().apply(houseStones)){
            throw new InvalidHouseException("Move cannot be applied, pit is empty");
        }

        log.info("Moving {} stones of house {} forward", houseStones, pitId);
        boardService.clearHouse(pitId, houses);
        int currentHouse = ++pitId;
        for (; houseStones > 0; houseStones--){
            int stones = boardService.getHouseStones(currentHouse, houses);
            houses.put(currentHouse, ++stones);
            if(currentHouse == GameConfig.NUMBER_OF_HOUSES){
                currentHouse = 0;
            }
            currentHouse++;
        }
        game.getBoard().setHouses(houses);
        gameService.printGame(game);
        return game;
    }

}
