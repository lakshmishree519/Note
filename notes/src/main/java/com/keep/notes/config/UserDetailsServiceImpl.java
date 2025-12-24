package com.keep.notes.config;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.keep.notes.model.User;
import com.keep.notes.repository.UserRepository;





@Service

public class UserDetailsServiceImpl implements UserDetailsService{

@Autowired
UserRepository userRepo;

@Override

public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

User user = userRepo.findByEmail(email).orElse(null);

if(user == null){

throw new UsernameNotFoundException("Invalid username");

}

return new UserPrinciple(user);

}

}

