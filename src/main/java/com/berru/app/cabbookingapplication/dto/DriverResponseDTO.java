package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import lombok.Data;

import java.util.List;

@Data
public class DriverResponseDTO {

    private Integer id;

    private UserResponseDTO user;

    private String driverLicenseNumber;

    private DriverAvailability availability;

    private List<VehicleResponseDTO> vehicles;

    private Double averageRating;

    private Integer totalRides;

    private LocationResponseDTO currentLocation;
}
