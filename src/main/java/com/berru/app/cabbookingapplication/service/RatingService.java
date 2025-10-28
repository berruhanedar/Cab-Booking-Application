package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.NewRatingRequestDTO;
import com.berru.app.cabbookingapplication.dto.RatingResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateRatingRequestDTO;

import java.util.List;

public interface RatingService {

    RatingResponseDTO createRating(NewRatingRequestDTO newRatingRequestDTO);

    RatingResponseDTO updateRating(Integer ratingId, UpdateRatingRequestDTO updateRatingRequestDTO);

    /**
     * 3
     * Belirli bir rating'i getirir.
     */
    RatingResponseDTO getRatingById(Integer ratingId);

    /**
     * 5
     * Bir sürücünün tüm ratinglerini listeler.
     */
    List<RatingResponseDTO> getRatingsByDriverId(Integer driverId);

    /**
     * 4
     * Bir booking'e ait rating varsa getirir.
     */
    RatingResponseDTO getRatingByBookingId(Integer bookingId);
}
