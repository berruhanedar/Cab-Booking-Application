package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import com.berru.app.cabbookingapplication.mapper.DriverMapper;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.service.DriverService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl  extends GenericRsqlService<Driver, DriverResponseDTO> implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper) {
        super(driverRepository,driverMapper :: toDriverResponseDTO);
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverResponseDTO createDriver(NewDriverRequestDTO newDriverRequestDTO) {
        return null;
    }

    @Override
    public DriverResponseDTO getDriverById(Integer id) {
        return null;
    }

    @Override
    public DriverResponseDTO getDriverByUserId(Integer userId) {
        return null;
    }

    @Override
    public PaginationResponse<DriverResponseDTO> listPaginated(int pageNo, int size) {
        return null;
    }

    @Override
    public List<DriverResponseDTO> searchDriverByRsql(String query) {
        return List.of();
    }

    @Override
    public List<DriverResponseDTO> findDriversByRating(Double minRating) {
        return List.of();
    }

    @Override
    public List<DriverResponseDTO> findDriversByAvailability(DriverAvailability availability) {
        return List.of();
    }

    @Override
    public List<VehicleResponseDTO> getVehiclesByDriverId(Integer driverId) {
        return List.of();
    }

    @Override
    public DriverResponseDTO updateDriver(Integer id, UpdateDriverRequestDTO updateDriverRequestDTO) {
        return null;
    }

    @Override
    public DriverResponseDTO updateDriverLocation(Integer id, LocationResponseDTO locationUpdate) {
        return null;
    }

    @Override
    public DriverResponseDTO updateDriverAvailability(Integer driverId, DriverAvailability availability) {
        return null;
    }

    @Override
    public DriverResponseDTO addVehicleToDriver(Integer driverId, Integer vehicleId) {
        return null;
    }

    @Override
    public DriverResponseDTO removeVehicleFromDriver(Integer driverId, Integer vehicleId) {
        return null;
    }

    @Override
    public void deleteDriverById(Integer id) {

    }
}
