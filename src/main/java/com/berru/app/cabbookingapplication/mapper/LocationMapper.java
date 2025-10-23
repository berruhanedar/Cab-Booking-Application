package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.LocationResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateLocationRequestDTO;
import com.berru.app.cabbookingapplication.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location toEmbeddable(UpdateLocationRequestDTO dto);

    LocationResponseDTO toLocationResponseDTO(Location embeddable);

    void updateEmbeddableFromDTO(UpdateLocationRequestDTO dto, @MappingTarget Location embeddable);
}
