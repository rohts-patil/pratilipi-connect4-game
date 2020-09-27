package com.connect4.game;

import com.connect4.game.move.MoveRequest;

public interface Processor {

  String process(MoveRequest moveRequest);
}
