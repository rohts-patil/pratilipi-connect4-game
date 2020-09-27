package com.connect4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Connect4Application {

  public static void main(String[] args) {
    SpringApplication.run(Connect4Application.class, args);
  }
}
