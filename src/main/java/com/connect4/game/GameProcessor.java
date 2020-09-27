package com.connect4.game;

import com.connect4.game.move.Move;
import com.connect4.game.move.MoveDAOService;
import com.connect4.game.move.MoveRequest;
import com.connect4.user.User;
import com.connect4.user.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.List;

@Component
public class GameProcessor implements Processor {
  @Autowired private UserDAOService userDAOService;

  @Autowired private MoveDAOService moveDAOService;

  @Autowired private GameStateDAOService gameStateDAOService;

  @Override
  public String process(MoveRequest moveRequest) {
    GameState gameState;
    ConnectGame connectGame;

    List movesTillNow = moveDAOService.getMoveByUuid(moveRequest.getUuid());
    String player = getPlayer(movesTillNow);

    boolean isStartOfGame = movesTillNow != null && movesTillNow.size() == 0;

    if (isStartOfGame) {
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
    String message = connectGame.toString();
    gameState.setConnectGame(SerializationUtils.serialize(connectGame));

    if (isStartOfGame) {
      gameStateDAOService.saveGameState(gameState);
    } else {
      gameStateDAOService.updateGameState(gameState);
    }

    message = checkWinAndPostChangesWin(moveRequest, message, connectGame, player);

    saveMove(moveRequest, player);

    return message;
  }

  private void saveMove(MoveRequest moveRequest, String player) {
    Move move = new Move();
    move.setColumnPlayed(moveRequest.getColumn());
    move.setPlayer(player);
    move.setUuid(moveRequest.getUuid());
    moveDAOService.saveMove(move);
  }

  private String checkWinAndPostChangesWin(
      MoveRequest moveRequest, String message, ConnectGame connectGame, String player) {
    if (connectGame.isWinningPlay()) {
      String name = player.equalsIgnoreCase("R") ? "Red" : "Yellow";
      message = message + " \n\n" + name + " won.";
      User user = userDAOService.getUser(moveRequest.getUuid());
      user.setGameOver(true);
      userDAOService.updateUser(user);
    }
    return message;
  }

  private String getPlayer(List movesTillNow) {
    String player;
    if (movesTillNow == null || movesTillNow.size() % 2 == 0) {
      player = "Y";
    } else {
      player = "R";
    }
    return player;
  }
}
