package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.NewVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.UpdateVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
import com.berru.app.cabbookingapplication.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toVehicle(NewVehicleRequestDTO newVehicleRequestDTO);

    @Mapping(source = "id", target = "id")
    VehicleResponseDTO toVehicleResponseDTO(Vehicle vehicle);

    void updateVehicleDTO(@MappingTarget UpdateVehicleRequestDTO updateVehicleRequestDTO, Vehicle vehicle);

}
