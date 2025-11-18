package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.config.security.CustomUserDetails;
import com.berru.app.cabbookingapplication.config.security.JwtTokenProvider;
import com.berru.app.cabbookingapplication.dto.AuthResponseDTO;
import com.berru.app.cabbookingapplication.dto.LoginRequestDTO;
import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.UserResponseDTO;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.enums.RoleStatus;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import com.berru.app.cabbookingapplication.exception.DuplicateEmailException;
import com.berru.app.cabbookingapplication.mapper.UserMapper;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
    }

    @Override
    public AuthResponseDTO register(NewUserRequestDTO newUserRequestDTO) {
        if (userRepository.existsByEmail(newUserRequestDTO.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        User user = userMapper.toUser(newUserRequestDTO);
        user.setPassword(passwordEncoder.encode(newUserRequestDTO.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(RoleStatus.CUSTOMER);

        User savedUser = userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(savedUser);

        String accessToken = jwtTokenProvider.generateToken(userDetails);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(savedUser);

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userResponseDTO)
                .build();
    }


    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public AuthResponseDTO refreshToken(String refreshToken) {
        return null;
    }
}
