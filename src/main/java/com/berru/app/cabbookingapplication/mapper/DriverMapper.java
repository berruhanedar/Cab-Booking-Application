package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.DriverResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewDriverRequestDTO;
import com.berru.app.cabbookingapplication.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Driver toEntity(NewDriverRequestDTO dto);

    DriverResponseDTO toDTO(Driver driver);

}