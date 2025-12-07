package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {

    boolean existsByDriverAndDateAndTimeAndStatusNot(Driver driver, LocalDate date, LocalTime time, BookingStatus status);

    List<Booking> findByStatus(BookingStatus status);

    List<Booking> findByUserId(Integer userId);

    List<Booking> findByDriverId(Integer driverId);

}
