package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {

    DriverResponseDTO createDriver(NewDriverRequestDTO newDriverRequestDTO);

    DriverResponseDTO getDriverById(Integer id);

    DriverResponseDTO getDriverByUserId(Integer userId);

    PaginationResponse<DriverResponseDTO> listPaginated(int pageNo, int size);

    List<DriverResponseDTO> searchDriverByRsql(String query);

    List<DriverResponseDTO> findDriversByRating(Double minRating);

    List<DriverResponseDTO> findDriversByAvailability(DriverAvailability availability);

    List<VehicleResponseDTO> getVehiclesByDriverId(Integer driverId);

    DriverResponseDTO updateDriver(Integer id, UpdateDriverRequestDTO updateDriverRequestDTO);

    DriverResponseDTO updateDriverLocation(Integer id, UpdateLocationRequestDTO locationUpdate);

    DriverResponseDTO updateDriverAvailability(Integer driverId, DriverAvailability availability);

    DriverResponseDTO addVehicleToDriver(Integer driverId, Integer vehicleId);

    DriverResponseDTO removeVehicleFromDriver(Integer driverId, Integer vehicleId);

    void deleteDriverById(Integer id);

}