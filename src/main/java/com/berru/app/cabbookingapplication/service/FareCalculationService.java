package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.entity.Booking;

public interface FareCalculationService {
    double calculateFare(Booking booking);
}