package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.NewRatingRequestDTO;
import com.berru.app.cabbookingapplication.dto.RatingResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateRatingRequestDTO;
import com.berru.app.cabbookingapplication.entity.Booking;

import java.util.List;

public interface RatingService {

    RatingResponseDTO createRating(NewRatingRequestDTO newRatingRequestDTO);

    RatingResponseDTO createRating(Booking booking);

    RatingResponseDTO updateRating(Integer ratingId, UpdateRatingRequestDTO updateRatingRequestDTO);

    RatingResponseDTO getRatingById(Integer ratingId);

    List<RatingResponseDTO> getRatingsByDriverId(Integer driverId);

    RatingResponseDTO getRatingByBookingId(Integer bookingId);
}
