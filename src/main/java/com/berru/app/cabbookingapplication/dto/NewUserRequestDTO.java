package com.berru.app.cabbookingapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserRequestDTO {

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotNull(message = "At least one address is required")
    @Size(min = 1, message = "At least one address is required")
    private Integer addressId;

}
