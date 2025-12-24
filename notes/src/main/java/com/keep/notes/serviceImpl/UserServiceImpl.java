package com.keep.notes.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keep.notes.exceptions.UserNotFoundException;
import com.keep.notes.model.User;
import com.keep.notes.repository.UserRepository;
import com.keep.notes.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public User deleteUser(Integer userId) {
        Optional<User> optUser = userRepo.findById(userId);
        if(optUser.isEmpty()){
          throw new UserNotFoundException("User not found");
        }
        userRepo.deleteById(userId);
        return optUser.get();
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepo.findAll();
        return users; 
    }

    @Override
    public User getUserById(Integer userId) {
      Optional<User> optUser = userRepo.findById(userId);
      if(optUser.isEmpty()){
        throw new UserNotFoundException("User not found");
      }
      return optUser.get();
    }
}
