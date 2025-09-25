package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.RoleStatus;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;

    private String username;

    private String email;

    private RoleStatus role;

}
