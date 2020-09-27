package com.connect4.game.move;

import com.connect4.game.move.MoveRequest;

public interface Validator {

    void validate(MoveRequest moveRequest);
}
