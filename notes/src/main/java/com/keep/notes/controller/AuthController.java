package com.keep.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keep.notes.dto.AuthRequestDto;
import com.keep.notes.dto.AuthResponseDto;
import com.keep.notes.exceptions.PasswordIncorrect;
import com.keep.notes.model.User;
import com.keep.notes.serviceImpl.AuthServiceImpl;

@RestController
@RequestMapping("/api/keepNotes")
@CrossOrigin(origins = "*")
public class AuthController {
   @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
         
          String res=authServiceImpl.registerUser(user);
           return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequestDto req){
        try{
           System.out.print("=================================================");
           AuthResponseDto response = authServiceImpl.loginUser(req);
            System.out.print(response+"=================================================");
           return ResponseEntity.status(200).body(response);
        }catch(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch(PasswordIncorrect e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
