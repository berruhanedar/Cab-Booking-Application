package com.berru.app.cabbookingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;

    private UserResponseDTO user;
}