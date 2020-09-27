package com.connect4.game;

import com.connect4.exception.GameException;
import com.connect4.game.move.Move;
import com.connect4.game.move.MoveDAOService;
import com.connect4.game.move.MoveRequest;
import com.connect4.user.User;
import com.connect4.user.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GameResource {

  @Autowired private UserDAOService userDAOService;

  @Autowired private MoveDAOService moveDAOService;

  @Autowired private GameStateDAOService gameStateDAOService;

  @PostMapping(path = "/start")
  public ResponseEntity<Object> start() {
    UUID uuid = UUID.randomUUID();
    User user = new User();
    user.setUuid(uuid.toString());
    String uuidTo = userDAOService.saveUser(user);
    return ResponseEntity.ok("Game ready. Your id for the game is:- " + uuidTo);
  }

  @PostMapping(path = "/move")
  public ResponseEntity<Object> move(@RequestBody MoveRequest moveRequest) {
    String message = "Default Message";
    GameState gameState;
    ConnectGame connectGame;
    if (isValidMove(moveRequest)) {
      List movesTillNow = moveDAOService.getMoveByUuid(moveRequest.getUuid());
      String player;
      if (movesTillNow == null || movesTillNow.size() % 2 == 0) {
        player = "Y";
      } else {
        player = "R";
      }

      if (movesTillNow != null && movesTillNow.size() == 0) {
        int height = 6;
        int width = 7;
        connectGame = new ConnectGame(width, height);
        gameState = new GameState();
        gameState.setUuid(moveRequest.getUuid());
      } else {
        gameState = gameStateDAOService.getGameState(moveRequest.getUuid());
        connectGame = (ConnectGame) SerializationUtils.deserialize(gameState.getConnectGame());
      }
      connectGame.chooseAndDrop(player.charAt(0), moveRequest.getColumn());
      message = connectGame.toString();
      gameState.setConnectGame(SerializationUtils.serialize(connectGame));
      if (movesTillNow != null && movesTillNow.size() == 0) {
        gameStateDAOService.saveGameState(gameState);
      } else {
        gameStateDAOService.updateGameState(gameState);
      }
      if (connectGame.isWinningPlay()) {
        message = message + " " + player + " won.";
        User user = userDAOService.getUser(moveRequest.getUuid());
        user.setGameOver(true);
        userDAOService.updateUser(user);
      }

      Move move = new Move();
      move.setColumnPlayed(moveRequest.getColumn());
      move.setPlayer(player);
      move.setUuid(moveRequest.getUuid());
      moveDAOService.saveMove(move);
    }

    return ResponseEntity.ok(message);
  }

  private boolean isValidMove(MoveRequest moveRequest) {
    if (!(0 <= moveRequest.getColumn() && moveRequest.getColumn() < 7)) {
      throw new GameException("Column must be between 0 and 6");
    }
    User user = userDAOService.getUser(moveRequest.getUuid());
    if (user == null || user.getGameOver()) {
      throw new GameException("Game already over or user not present.");
    }
    return true;
  }
}
