package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.DriverResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewDriverRequestDTO;
import com.berru.app.cabbookingapplication.dto.UpdateDriverRequestDTO;
import com.berru.app.cabbookingapplication.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface DriverMapper {

    DriverResponseDTO toDriverResponseDTO(Driver driver);

    @Mapping(target = "vehicles", ignore = true)
    Driver toDriver(NewDriverRequestDTO newDriverRequestDTO);

    void updateDriverFromDTO(UpdateDriverRequestDTO dto, @MappingTarget Driver driver);
}