package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.entity.Driver;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingValidationService {
    void validateDriverAvailability(Driver driver, LocalDate date, LocalTime time);
}
