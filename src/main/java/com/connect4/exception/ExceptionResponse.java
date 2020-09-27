package com.connect4.exception;

import lombok.Data;

import java.util.Date;

public @Data class ExceptionResponse {
  private Date timeStamp;

  private String message;

  private String details;

  public ExceptionResponse(Date timeStamp, String message, String details) {
    this.timeStamp = timeStamp;
    this.message = message;
    this.details = details;
  }
}
