package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.VehicleTypeStatus;
import lombok.Data;

@Data
public class VehicleResponseDTO {

    private String plate;

    private String model;

    private String color;

    private VehicleTypeStatus type;
}
