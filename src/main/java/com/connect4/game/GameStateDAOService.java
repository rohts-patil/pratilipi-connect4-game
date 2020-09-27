package com.connect4.game;

public interface GameStateDAOService {
  void saveGameState(GameState gameState);

  GameState getGameState(String uuid);

  void updateGameState(GameState gameState);
}
