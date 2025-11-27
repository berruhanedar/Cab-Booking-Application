package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.LocationResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateLocationRequestDTO;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.service.LocationService;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final DriverRepository driverRepository;

    @Override
    public LocationResponseDTO updateDriverLocation(Integer driverId, UpdateLocationRequestDTO dto) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + driverId));

        driver.getCurrentLocation().setLatitude(dto.getLatitude());
        driver.getCurrentLocation().setLongitude(dto.getLongitude());
        driver.getCurrentLocation().setAddress(dto.getAddress());

        driverRepository.save(driver);

        return convertToLocationResponse(driver);
    }

    @Override
    public LocationResponseDTO getDriverLocation(Integer driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + driverId));

        return convertToLocationResponse(driver);
    }

    @Override
    public double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }

    @Override
    public List<Integer> findNearestDrivers(double userLat, double userLng, int limit) {
        List<Driver> drivers = driverRepository.findAll();

        return drivers.stream()
                .filter(d -> d.getCurrentLocation() != null)
                .sorted(Comparator.comparing(
                        d -> calculateDistanceKm(
                                userLat,
                                userLng,
                                d.getCurrentLocation().getLatitude(),
                                d.getCurrentLocation().getLongitude()
                        )
                ))
                .limit(limit)
                .map(Driver::getId)
                .collect(Collectors.toList());
    }

    @Override
    public int estimateArrivalTimeInMinutes(double distanceKm) {
        double timeHours = distanceKm / 40.0;
        return (int) Math.round(timeHours * 60);
    }

    private LocationResponseDTO convertToLocationResponse(Driver driver) {
        LocationResponseDTO dto = new LocationResponseDTO();
        dto.setLatitude(driver.getCurrentLocation().getLatitude());
        dto.setLongitude(driver.getCurrentLocation().getLongitude());
        dto.setAddress(driver.getCurrentLocation().getAddress());

        return dto;
    }
}
