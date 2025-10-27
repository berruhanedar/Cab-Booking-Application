package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    Rating toRating(NewRatingRequestDTO newRatingRequestDTO);

    RatingResponseDTO toRatingResponseDTO(Rating rating);

    void updateRatingFromDTO(UpdateRatingRequestDTO updateRatingRequestDTO, @MappingTarget Rating rating);

}