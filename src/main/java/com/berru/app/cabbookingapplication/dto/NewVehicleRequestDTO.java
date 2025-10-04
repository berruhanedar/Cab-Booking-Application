package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.VehicleStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class NewVehicleRequestDTO {

    @NotBlank(message = "Vehicle plate cannot be blank")
    @Size(min = 5, max = 15, message = "Vehicle plate must be between 5 and 15 characters")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{1,3}[0-9]{2,4}$", message = "Vehicle plate must be in valid format (e.g., 34ABC123)")
    private String plate;

    @NotBlank(message = "Vehicle model cannot be blank")
    @Size(min = 2, max = 50, message = "Vehicle model must be between 2 and 50 characters")
    private String model;

    @Size(max = 20, message = "Vehicle color must be at most 20 characters")
    private String color;

    @NotNull(message = "Vehicle type cannot be null")
    private VehicleType type = VehicleType.STANDARD;

    @NotNull(message = "Vehicle energy type cannot be null")
    private VehicleEnergyType energyType = VehicleEnergyType.FUEL;

    @NotNull(message = "Vehicle status cannot be null")
    private VehicleStatus status = VehicleStatus.AVAILABLE;

}
