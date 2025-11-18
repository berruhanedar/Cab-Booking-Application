package com.berru.app.cabbookingapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;

    private UserResponseDTO user;
}