package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.ComfortStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateBookingFormRequestDTO {

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name must be at most 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$" , message = "Name must contain only alphabet")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$" , message = "Lastname must contain only alphabet")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "From location cannot be blank")
    @Size(max = 100, message = "From location must be at most 100 characters")
    private String from;

    @NotBlank(message = "To location cannot be blank")
    @Size(max = 100, message = "To location must be at most 100 characters")
    private String to;

    @NotNull(message = "Time cannot be null")
    private LocalTime time;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Adult count cannot be null")
    @Min(value = 1, message = "At least 1 adult is required")
    @Max(value = 4, message = "Adult can be at most 4")
    private Integer adult;

    @NotNull(message = "Comfort cannot be null")
    private ComfortStatus comfort;

    @NotNull(message = "Children count cannot be null")
    @Min(value = 0, message = "Children count cannot be negative")
    @Max(value = 3, message = "Children can be at most 3")
    private Integer children;

    @Size(max = 500, message = "Message must be at most 500 characters")
    private String message;
}
