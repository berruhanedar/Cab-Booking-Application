package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.entity.Booking;

import com.berru.app.cabbookingapplication.service.FareCalculationService;
import org.springframework.stereotype.Service;

@Service
public class FareCalculationServiceImpl implements FareCalculationService {

    private final DistanceService distanceService;

    public FareCalculationServiceImpl(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Override
    public double calculateFare(Booking booking) {
        double baseFare = 50.0;

        double distanceKm = distanceService.getDistanceInKm(
                booking.getFrom(),
                booking.getTo()
        );

        double distanceFare = distanceKm * 2.0;

        double vehicleMultiplier;
        switch (booking.getVehicleType()) {
            case LUXURY -> vehicleMultiplier = 2.0;
            case XL -> vehicleMultiplier = 1.8;
            case COMFORT -> vehicleMultiplier = 1.2;
            default -> vehicleMultiplier = 1.0;
        }

        double passengerExtra = booking.getAdult() + (booking.getChildren() * 0.5);

        return (baseFare + distanceFare) * vehicleMultiplier + passengerExtra;
    }
}
