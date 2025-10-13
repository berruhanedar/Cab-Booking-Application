package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.DriverResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewDriverRequestDTO;
import com.berru.app.cabbookingapplication.dto.UpdateDriverRequestDTO;
import com.berru.app.cabbookingapplication.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverResponseDTO toDriverResponseDTO(Driver driver);

    Driver toDriver(NewDriverRequestDTO newDriverRequestDTO);

    void updateDriverFromDTO(UpdateDriverRequestDTO dto, @MappingTarget Driver driver);
}