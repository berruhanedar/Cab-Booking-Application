package com.berru.app.cabbookingapplication.dto;

import lombok.Data;

@Data
public class DriverResponseDTO {

    private Integer id;

    private UserResponseDTO user;

    private String driverLicenseNumber;

    private VehicleResponseDTO vehicle;

    private Double rating;

    private Integer totalRides;

    private LocationResponseDTO currentLocation;
}
