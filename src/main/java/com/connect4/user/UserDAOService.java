package com.connect4.user;

public interface UserDAOService {
  String saveUser(User user);

  User getUser(String uuid);

  void updateUser(User user);
}
