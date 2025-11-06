package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.enums.BookingStatus;
import com.berru.app.cabbookingapplication.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BookingValidationService {

    private final BookingRepository bookingRepository;

    public BookingValidationService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void validateDriverAvailability(Driver driver, LocalDate date, LocalTime time) {
        boolean isDriverAvailable = bookingRepository.existsByDriverAndDateAndTimeAndStatusNot(
                driver, date, time, BookingStatus.CANCELLED);
        if (isDriverAvailable) {
            throw new IllegalStateException(String.format("Driver %s is not available at %s %s", driver.getId(), date, time));
        }
    }
}
