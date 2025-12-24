package com.keep.notes.service;

import com.keep.notes.dto.AuthRequestDto;
import com.keep.notes.dto.AuthResponseDto;
import com.keep.notes.model.User;

public interface AuthService {
    String registerUser(User user);
     AuthResponseDto loginUser(AuthRequestDto req);
}
