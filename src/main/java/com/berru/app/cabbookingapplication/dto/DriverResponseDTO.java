package com.berru.app.cabbookingapplication.dto;

import lombok.Data;

@Data
public class DriverResponseDTO {

    private Integer id;

    private UserResponseDTO user;

    private String driverLicenseNumber;

    private String vehiclePlate;

    private String vehicleModel;

    private String vehicleColor;

}
