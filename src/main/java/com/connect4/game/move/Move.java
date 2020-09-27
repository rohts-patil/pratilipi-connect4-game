package com.connect4.game.move;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @JsonIgnore private String uuid;

  private String player;

  private int columnPlayed;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date timeStamp;
}
