package com.connect4.user;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class UserDAOServiceImpl implements UserDAOService {

  @PersistenceContext private EntityManager entityManager;

  public String saveUser(User user) {
    entityManager.persist(user);
    return user.getUuid();
  }

  public User getUser(String uuid) {
    return entityManager
        .createQuery("SELECT u from User u WHERE u.uuid = :uuid", User.class)
        .setParameter("uuid", uuid)
        .getSingleResult();
  }

  public void updateUser(User user) {
    entityManager.merge(user);
  }
}
