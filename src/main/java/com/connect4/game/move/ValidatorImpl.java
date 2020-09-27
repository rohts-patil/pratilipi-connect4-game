package com.connect4.game.move;

import com.connect4.exception.GameException;
import com.connect4.user.User;
import com.connect4.user.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorImpl implements Validator {
  @Autowired private UserDAOService userDAOService;

  @Override
  public void validate(MoveRequest moveRequest) {
    if (!(0 <= moveRequest.getColumn() && moveRequest.getColumn() < 7)) {
      throw new GameException("Column must be between 0 and 6");
    }
    User user = userDAOService.getUser(moveRequest.getUuid());
    if (user == null || user.getGameOver()) {
      throw new GameException("Game already over or user not present.");
    }
  }
}
