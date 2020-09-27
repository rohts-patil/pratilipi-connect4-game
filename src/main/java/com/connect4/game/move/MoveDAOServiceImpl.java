package com.connect4.game.move;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class MoveDAOServiceImpl implements MoveDAOService {

  @PersistenceContext private EntityManager entityManager;

  public int saveMove(Move move) {
    entityManager.persist(move);
    return move.getId();
  }

  public List getMoveByUuid(String uuid) {
    return entityManager
        .createQuery("SELECT t FROM Move t where t.uuid = :uuid", Move.class)
        .setParameter("uuid", uuid)
        .getResultList();
  }
}
