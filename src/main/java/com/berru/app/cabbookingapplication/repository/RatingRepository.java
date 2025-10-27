package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> , JpaSpecificationExecutor<Rating> {
    Optional<Rating> findByBooking(Booking booking);
}