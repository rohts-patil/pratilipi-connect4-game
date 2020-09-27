package com.connect4.game.move;

import lombok.Data;

public @Data class MoveRequest {
  private String uuid;

  private int column;
}
