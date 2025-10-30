package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.NewRatingRequestDTO;
import com.berru.app.cabbookingapplication.dto.RatingResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateRatingRequestDTO;
import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.Rating;
import com.berru.app.cabbookingapplication.exception.DuplicateRatingException;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.RatingMapper;
import com.berru.app.cabbookingapplication.repository.BookingRepository;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.repository.RatingRepository;
import com.berru.app.cabbookingapplication.service.RatingService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RatingServiceImpl extends GenericRsqlService<Rating, RatingResponseDTO> implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final BookingRepository bookingRepository;
    private final DriverRepository driverRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper, BookingRepository bookingRepository,DriverRepository driverRepository) {
        super(ratingRepository, ratingMapper::toRatingResponseDTO);
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public RatingResponseDTO createRating(NewRatingRequestDTO newRatingRequestDTO) {
        Booking booking = bookingRepository.findById(newRatingRequestDTO.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + newRatingRequestDTO.getBookingId()));
        ratingRepository.findByBooking(booking).ifPresent(existing -> {
            throw new DuplicateRatingException("A rating already exists for this booking.");
        });
        Rating rating = ratingMapper.toRating(newRatingRequestDTO);
        rating.setBooking(booking);
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toRatingResponseDTO(savedRating);
    }

    @Override
    public RatingResponseDTO updateRating(Integer ratingId, UpdateRatingRequestDTO updateRatingRequestDTO) {
        Rating existingRating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with ID: " + ratingId));
        ratingMapper.updateRatingFromDTO(updateRatingRequestDTO, existingRating);
        Rating updatedRating = ratingRepository.save(existingRating);
        return ratingMapper.toRatingResponseDTO(updatedRating);
    }

    @Override
    @Transactional(readOnly = true)
    public RatingResponseDTO getRatingById(Integer ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with ID: " + ratingId));
        return ratingMapper.toRatingResponseDTO(rating);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingResponseDTO> getRatingsByDriverId(Integer driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found with ID: " + driverId));
        List<Rating> ratings = ratingRepository.findByDriver(driver);
        return ratings.stream()
                .map(ratingMapper::toRatingResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RatingResponseDTO getRatingByBookingId(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Booking not found with ID: " + bookingId));
        Rating rating = ratingRepository.findByBooking(booking)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for booking with ID: " + bookingId));
        return ratingMapper.toRatingResponseDTO(rating);
    }
}