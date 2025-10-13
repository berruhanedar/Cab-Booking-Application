package com.berru.app.cabbookingapplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class DriverResponseDTO {

    private Integer id;

    private UserResponseDTO user;

    private String driverLicenseNumber;

    private List<VehicleResponseDTO> vehicles;

    private Double rating;

    private Integer totalRides;

    private LocationResponseDTO currentLocation;
}
