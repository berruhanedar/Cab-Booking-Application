package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.NewVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
import com.berru.app.cabbookingapplication.entity.Vehicle;
import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.mapper.VehicleMapper;
import com.berru.app.cabbookingapplication.repository.VehicleRepository;
import com.berru.app.cabbookingapplication.service.VehicleService;

import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl extends GenericRsqlService<Vehicle,VehicleResponseDTO> implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final PaginationMapper paginationMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository,
                              VehicleMapper vehicleMapper,
                              PaginationMapper paginationMapper) {
        super(vehicleRepository, vehicleMapper ::toVehicleResponseDTO);
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.paginationMapper = paginationMapper;
    }

    @Override
    @Transactional
    public VehicleResponseDTO createVehicle(NewVehicleRequestDTO newVehicleRequestDTO) {
        Vehicle vehicle = vehicleMapper.toVehicle(newVehicleRequestDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleResponseDTO(savedVehicle);
    }

    @Override
    @Transactional
    public VehicleResponseDTO getVehicleById(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        return vehicleMapper.toVehicleResponseDTO(vehicle);
    }

    @Override
    @Transactional
    public VehicleResponseDTO getVehicleByPlate(String plate) {
        Vehicle vehicle = vehicleRepository.findByPlate(plate).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with plate: " + plate));
        return vehicleMapper.toVehicleResponseDTO(vehicle);
    }

    @Override
    @Transactional
    public PaginationResponse<VehicleResponseDTO> listPaginated(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);

        return paginationMapper.toPaginationResponse(vehiclePage, vehicleMapper::toVehicleResponseDTO);
    }

    @Override
    @Transactional
    public List<VehicleResponseDTO> listVehiclesByType(VehicleType type) {
        List<Vehicle> vehicles = vehicleRepository.findByType(type);
        return vehicles.stream()
                .map(vehicleMapper::toVehicleResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<VehicleResponseDTO> listVehiclesByEnergy(VehicleEnergyType energyType) {
       List<Vehicle> vehicles = vehicleRepository.findByEnergy(energyType);
       return vehicles.stream()
                .map(vehicleMapper::toVehicleResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<VehicleResponseDTO> listVehiclesByStatus(VehicleStatus status) {
        List<Vehicle> vehicles = vehicleRepository.findyStatus(status);
        return vehicles.stream()
                .map(vehicleMapper::toVehicleResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> searchVehicleByRsql(String query) {
        return searchByRsql(query);
    }

    @Override
    public VehicleResponseDTO updateVehicle(Integer id, UpdateVehicleRequestDTO updateVehicleRequestDTO) {
        return null;
    }

    @Override
    public VehicleResponseDTO updateVehicleStatus(Integer id, VehicleStatus status) {
        return null;
    }

    @Override
    public void deleteVehicleById(Integer id) {

    }
}
