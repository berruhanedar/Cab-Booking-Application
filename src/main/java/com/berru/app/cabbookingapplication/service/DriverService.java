package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {

    DriverResponseDTO registerDriver(NewDriverRequestDTO newDriverRequestDTO);

    DriverResponseDTO getDriverById(Integer id);

    DriverResponseDTO getDriverByUserId(Integer userId);

    PaginationResponse<DriverResponseDTO> listPaginated(int pageNo, int size);

    List<DriverResponseDTO> searchDriverByRsql(String query);

    DriverResponseDTO updateDriver(Integer id, UpdateDriverRequestDTO updateDriverRequestDTO);

    DriverResponseDTO updateDriverLocation(Integer id, LocationResponseDTO locationUpdate);

    void deleteDriverById(Integer id);

    List<DriverResponseDTO> findDriversByRating(Double minRating);

    DriverResponseDTO incrementTotalRides(Integer id);

    DriverResponseDTO updateDriverAvailability(Integer driverId, DriverAvailability availability);

    // TODO: İleride araç atama/çıkarma işlemleri buraya eklenebilir
    // Örn: updateDriverVehicle(Integer driverId, Integer vehicleId)
}