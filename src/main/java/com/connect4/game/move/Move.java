package com.connect4.game.move;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public @Data class Move {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String uuid;

  private String player;

  private int columnPlayed;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date timeStamp;
}
