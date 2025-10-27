package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.NewRatingRequestDTO;
import com.berru.app.cabbookingapplication.dto.RatingResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateRatingRequestDTO;
import com.berru.app.cabbookingapplication.entity.Rating;
import com.berru.app.cabbookingapplication.mapper.RatingMapper;
import com.berru.app.cabbookingapplication.repository.RatingRepository;
import com.berru.app.cabbookingapplication.service.RatingService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;

import java.util.List;

public class RatingServiceImpl extends GenericRsqlService<Rating, RatingResponseDTO> implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        super(ratingRepository, ratingMapper::toRatingResponseDTO);
        this.ratingRepository = ratingRepository;
    }

    @Override
    public RatingResponseDTO createRating(NewRatingRequestDTO newRatingRequestDTO) {
        return null;
    }

    @Override
    public RatingResponseDTO updateRating(UpdateRatingRequestDTO updateRatingRequestDTO) {
        return null;
    }

    @Override
    public RatingResponseDTO getRatingById(Integer ratingId) {
        return null;
    }

    @Override
    public List<RatingResponseDTO> getRatingsByDriverId(Integer driverId) {
        return List.of();
    }

    @Override
    public RatingResponseDTO getRatingByBookingId(Integer bookingId) {
        return null;
    }
}