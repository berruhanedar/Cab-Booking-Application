package com.berru.app.cabbookingapplication.dto;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class NewBookingRequestDTO {

    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    private Integer driverId;

    @NotBlank(message = "From location cannot be blank")
    @Size(max = 100, message = "From location must be at most 100 characters")
    private String from;

    @NotBlank(message = "To location cannot be blank")
    @Size(max = 100, message = "To location must be at most 100 characters")
    private String to;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Time cannot be null")
    private LocalTime time;

    @NotNull(message = "Vehicle type cannot be null")
    private VehicleType vehicleType;

    @Min(value = 1, message = "At least 1 adult must be specified")
    private Integer adult;

    @Min(value = 0, message = "Children count cannot be negative")
    private Integer children;

    @Size(max = 500, message = "Message must be at most 500 characters")
    private String message;

}
