package com.keep.notes.service;

import java.util.List;

import com.keep.notes.model.User;

public interface UserService {
      User getUserById(Integer userId);
     List<User> getAllUser();
     User deleteUser(Integer userId);
}
