package com.connect4.game;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public @Data class GameState {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "uuid", unique = true, nullable = false)
  private String uuid;

  private byte[] connectGame;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date timeStamp;
}
