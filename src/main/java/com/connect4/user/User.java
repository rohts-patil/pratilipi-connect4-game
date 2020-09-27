package com.connect4.user;

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
public @Data class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "uuid", unique = true, nullable = false)
  private String uuid;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date timeStamp;

  private Boolean gameOver = Boolean.FALSE;
}
