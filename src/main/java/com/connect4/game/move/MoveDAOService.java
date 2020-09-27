package com.connect4.game.move;

import java.util.List;

public interface MoveDAOService {
  int saveMove(Move move);

  List getMoveByUuid(String uuid);
}
