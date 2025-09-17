package com.berru.app.cabbookingapplication.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewContactFormRequestDTO {
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Digits(integer = 15, fraction = 0, message = "Phone number must be numeric and up to 15 digits")
    private Long phone;

    @NotNull(message = "Message cannot be null")
    @Size(min = 5, max = 500, message = "Message must be between 5 and 500 characters")
    private String message;

}
