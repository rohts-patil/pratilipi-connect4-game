package com.connect4.game;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class GameStateDAOServiceImpl implements GameStateDAOService {
  @PersistenceContext private EntityManager entityManager;

  public void saveGameState(GameState gameState) {
    entityManager.persist(gameState);
  }

  public GameState getGameState(String uuid) {
    return entityManager
        .createQuery("SELECT u from GameState u WHERE u.uuid = :uuid", GameState.class)
        .setParameter("uuid", uuid)
        .getSingleResult();
  }

  public void updateGameState(GameState gameState) {
    entityManager.merge(gameState);
  }
}
