package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.AuthResponseDTO;
import com.berru.app.cabbookingapplication.dto.LoginRequestDTO;
import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;

public interface AuthService {

    AuthResponseDTO register(NewUserRequestDTO newUserRequestDTO);

    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);

    void logout(String token);

    AuthResponseDTO refreshToken(String refreshToken);
}
