package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> , JpaSpecificationExecutor<Rating> {

    Optional<Rating> findByBooking(Booking booking);

    List<Rating> findByDriver(Driver driver);

    @Query("SELECT COALESCE(AVG(r.score), 0) FROM Rating r WHERE r.driver.id = :driverId")
    Double findAverageScoreByDriverId(@Param("driverId") Integer driverId);

}