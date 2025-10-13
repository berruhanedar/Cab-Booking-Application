package com.berru.app.cabbookingapplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class NewDriverRequestDTO {

    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    @NotBlank(message = "Driver license number cannot be blank")
    @Size(min = 6, max = 20, message = "Driver license number must be between 6 and 20 characters")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Driver license number must contain only uppercase letters and numbers")
    private String driverLicenseNumber;

    @NotNull(message = "Vehicle IDs cannot be null")
    @Size(min = 1, message = "At least one vehicle must be assigned")
    private List<Integer> vehicleIds;

    @NotNull(message = "Current location cannot be null")
    private LocationUpdateDTO currentLocation;

}
