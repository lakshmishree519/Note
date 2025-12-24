package com.keep.notes.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keep.notes.config.JwtUtils;
import com.keep.notes.dto.AuthRequestDto;
import com.keep.notes.dto.AuthResponseDto;
import com.keep.notes.exceptions.PasswordIncorrect;
import com.keep.notes.exceptions.UserNotFoundException;
import com.keep.notes.model.User;
import com.keep.notes.repository.UserRepository;
import com.keep.notes.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepo;

     @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String registerUser(User user) {
          
          if(user.getEmail().trim().isEmpty()){
            return "email cannot be blank";
          }
          if(user.getName().trim().isEmpty()){
            return "name cannot be blank";
          }
          if(userRepo.findByEmail(user.getEmail()).isPresent()){
            return "Email alreday exists";
          }
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepo.save(user);
         return "user registerd successfully";
    }


    @Override
    public AuthResponseDto loginUser(AuthRequestDto req) {
        Optional<User> user = userRepo.findByEmail(req.getEmail());
         if(user.isEmpty()){
            throw new UserNotFoundException("Email is incorrect");
         } 
         if(!passwordEncoder.matches(req.getPassword(),user.get().getPassword())){
            throw new PasswordIncorrect("Password Incorrect");
         }
         

         UserDetails userDetails=userDetailsService.loadUserByUsername(req.getEmail());
         String token = jwtUtils.generateToken(userDetails.getUsername());
        return new AuthResponseDto(token, user.get().getUserId(), user.get().getName());
    }

}
