package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.NewVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
import com.berru.app.cabbookingapplication.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toVehicle(NewVehicleRequestDTO newVehicleRequestDTO);

    VehicleResponseDTO toVehicleResponseDTO(Vehicle vehicle);

    void updateVehicleDTO(@MappingTarget NewVehicleRequestDTO newVehicleRequestDTO, Vehicle vehicle);

}
