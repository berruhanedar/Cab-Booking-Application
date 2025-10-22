package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.NewRatingRequestDTO;
import com.berru.app.cabbookingapplication.dto.RatingResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateRatingRequestDTO;

import java.util.List;

public interface RatingService {

    /**
     * 1
     * Yeni bir rating oluşturur.
     * Tip (bahşiş) varsa PaymentService üzerinden ödemeyi gerçekleştirir.
     */
    RatingResponseDTO createRating(NewRatingRequestDTO newRatingRequestDTO);

    /**
     * 2
     * Mevcut bir rating'i günceller.
     */
    RatingResponseDTO updateRating(UpdateRatingRequestDTO updateRatingRequestDTO);

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
