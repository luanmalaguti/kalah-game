package com.luan.kalah.controller;

import com.luan.kalah.service.GameService;
import com.luan.kalah.service.MoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Application @{@link RestController} who offers the endpoints to interact with the game
 */
@RestController
@Validated
@RequestMapping(value="/games")
public class GameController {

    private static Logger log = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;
    @Autowired
    private MoveService moveService;

    @PostMapping()
    public ResponseEntity<?> createGame(){
        try {
            return new ResponseEntity<>(gameService.createGame(), HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Create game error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{gameId}/pits/{pitId}")
    public ResponseEntity<?> move(@PathVariable long gameId, @PathVariable int pitId){
        try {
            return new ResponseEntity<>(moveService.move(gameId, pitId), HttpStatus.OK);
        } catch (Exception e) {
           log.error("Game move error: ", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
