package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleResponseDTO {

    private String plate;

    private String model;

    private String color;

    private VehicleType type;

    private VehicleEnergyType energyType;

    private VehicleStatus status;

    private DriverResponseDTO driver;

}
