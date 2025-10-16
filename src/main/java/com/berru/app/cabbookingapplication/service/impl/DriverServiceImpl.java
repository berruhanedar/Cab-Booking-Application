package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.entity.Vehicle;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import com.berru.app.cabbookingapplication.exception.DuplicateDriverProfileException;
import com.berru.app.cabbookingapplication.mapper.DriverMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.service.DriverService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class DriverServiceImpl  extends GenericRsqlService<Driver, DriverResponseDTO> implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final UserRepository userRepository;
    private final PaginationMapper paginationMapper;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper,UserRepository userRepository, PaginationMapper paginationMapper) {
        super(driverRepository,driverMapper :: toDriverResponseDTO);
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.userRepository = userRepository;
        this.paginationMapper = paginationMapper;
    }

    @Override
    public DriverResponseDTO createDriver(NewDriverRequestDTO newDriverRequestDTO) {
        User user = userRepository.findById(newDriverRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + newDriverRequestDTO.getUserId()));

        if (driverRepository.existsByUserId(user.getId())) {
            throw new DuplicateDriverProfileException("This user already has a driver profile.");
        }

        Driver driver = driverMapper.toDriver(newDriverRequestDTO);
        driver.setUser(user);

        Driver savedDriver = driverRepository.save(driver);
        return  driverMapper.toDriverResponseDTO(savedDriver);
    }

    @Override
    @Transactional(readOnly = true)
    public DriverResponseDTO getDriverById(Integer id) {
        Driver driver = driverRepository.findById(id).orElseThrow(()-> new RuntimeException("Driver not   found with ID: " + id));
        return  driverMapper.toDriverResponseDTO(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public DriverResponseDTO getDriverByUserId(Integer userId) {
        Driver driver = driverRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException("Driver not   found with ID: " + userId));
        return  driverMapper.toDriverResponseDTO(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<DriverResponseDTO> listPaginated(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Driver> driverPage = driverRepository.findAll(pageable);
        return paginationMapper.toPaginationResponse(driverPage, driverMapper::toDriverResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponseDTO> searchDriverByRsql(String query) {
        return searchByRsql(query);
    }

    @Override
    public List<DriverResponseDTO> findDriversByRating(Double minRating) {
        List<Driver> drivers = driverRepository.findByRatingGreaterThanEqual(minRating);
        return drivers.stream()
                .map(driverMapper::toDriverResponseDTO)
                .toList();
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
