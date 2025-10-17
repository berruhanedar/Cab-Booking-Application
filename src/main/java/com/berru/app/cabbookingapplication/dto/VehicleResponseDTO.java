package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleResponseDTO {

    private Integer id;

    private String plate;

    private String model;

    private String color;

    private VehicleType type;

    private VehicleEnergyType energyType;

    private VehicleStatus status;

}
