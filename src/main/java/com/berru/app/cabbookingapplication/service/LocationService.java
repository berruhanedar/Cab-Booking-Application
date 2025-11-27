package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.LocationResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateLocationRequestDTO;

import java.util.List;

public interface LocationService {

    LocationResponseDTO updateDriverLocation(Integer driverId, UpdateLocationRequestDTO updateLocationRequestDTO);

    LocationResponseDTO getDriverLocation(Integer driverId);

    double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2);

    List<Integer> findNearestDrivers(double userLat, double userLng, int limit);

    int estimateArrivalTimeInMinutes(double distanceKm);

}
