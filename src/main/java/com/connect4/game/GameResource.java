package com.connect4.game;

import com.connect4.game.move.MoveDAOService;
import com.connect4.game.move.MoveRequest;
import com.connect4.game.move.Validator;
import com.connect4.user.User;
import com.connect4.user.UserDAOService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GameResource {

  @Autowired private UserDAOService userDAOService;

  @Autowired private MoveDAOService moveDAOService;

  @Autowired private Validator validator;

  @Autowired private Processor processor;

  @PostMapping(path = "/start")
  public ResponseEntity<Object> start() {
    UUID uuid = UUID.randomUUID();
    User user = new User();
    user.setUuid(uuid.toString());
    String uuidTo = userDAOService.saveUser(user);
    return ResponseEntity.ok("Game ready. Your id for the game is:- " + uuidTo);
  }

  @PostMapping(path = "/move")
  public ResponseEntity<Object> move(@RequestBody MoveRequest moveRequest) {

    validator.validate(moveRequest);

    return ResponseEntity.ok(processor.process(moveRequest));
  }

  @GetMapping(path = "/getAllMoves/{uuid}")
  public ResponseEntity<Object> getAllMoves(@PathVariable String uuid) throws Exception {
    List movesTillNow = moveDAOService.getMoveByUuid(uuid);
    ObjectMapper objectMapper = new ObjectMapper();
    String json = null;
    try {
      json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(movesTillNow);
    } catch (JsonProcessingException e) {
      throw new Exception();
    }
    return ResponseEntity.ok(json);
  }
}
