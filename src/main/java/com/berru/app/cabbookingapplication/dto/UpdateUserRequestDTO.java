package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.RoleStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    private RoleStatus role;
}
