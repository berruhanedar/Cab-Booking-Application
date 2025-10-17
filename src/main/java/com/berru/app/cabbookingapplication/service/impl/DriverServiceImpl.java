package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;import com.berru.app.cabbookingapplication.entity.Address;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.entity.Vehicle;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import com.berru.app.cabbookingapplication.enums.RoleStatus;
import com.berru.app.cabbookingapplication.exception.DuplicateDriverProfileException;
import com.berru.app.cabbookingapplication.mapper.DriverMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.mapper.VehicleMapper;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.repository.VehicleRepository;
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
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper,UserRepository userRepository, PaginationMapper paginationMapper,VehicleRepository vehicleRepository,VehicleMapper vehicleMapper) {
        super(driverRepository,driverMapper :: toDriverResponseDTO);
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.userRepository = userRepository;
        this.paginationMapper = paginationMapper;
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public DriverResponseDTO createDriver(NewDriverRequestDTO newDriverRequestDTO) {
        User user = userRepository.findById(newDriverRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + newDriverRequestDTO.getUserId()));

        if (user.getRole() != RoleStatus.DRIVER) {
            throw new RuntimeException("User role must be DRIVER to create a driver profile.");
        }

        if (driverRepository.existsByUserId(user.getId())) {
            throw new DuplicateDriverProfileException("This user already has a driver profile.");
        }

        Driver driver = driverMapper.toDriver(newDriverRequestDTO);
        driver.setUser(user);

        Vehicle vehicle = vehicleRepository.findById(newDriverRequestDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + newDriverRequestDTO.getVehicleId()));
        vehicle.setDriver(driver);
        driver.getVehicles().add(vehicle);

        vehicleRepository.save(vehicle);

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
    @Transactional(readOnly = true)
    public List<DriverResponseDTO> findDriversByRating(Double minRating) {
        List<Driver> drivers = driverRepository.findByRatingGreaterThanEqual(minRating);
        return drivers.stream()
                .map(driverMapper::toDriverResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponseDTO> findDriversByAvailability(DriverAvailability availability) {
        List<Driver> drivers = driverRepository.findByAvailability(availability);
        return drivers.stream()
                .map(driverMapper::toDriverResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> getVehiclesByDriverId(Integer driverId) {
       List<Vehicle> vehicles = vehicleRepository.findByDriverId(driverId);
       return vehicles.stream()
               .map(vehicleMapper::toVehicleResponseDTO)
               .toList();
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
