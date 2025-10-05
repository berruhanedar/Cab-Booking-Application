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
import com.berru.app.cabbookingapplication.mapper.VehicleMapper;
import com.berru.app.cabbookingapplication.repository.VehicleRepository;
import com.berru.app.cabbookingapplication.service.VehicleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

   private final VehicleRepository vehicleRepository;
   private final VehicleMapper vehicleMapper;

   public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
       this.vehicleRepository = vehicleRepository;
       this.vehicleMapper = vehicleMapper;
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
    public VehicleResponseDTO getVehicleByPlate(String plate) {
        return null;
    }

    @Override
    public PaginationResponse<VehicleResponseDTO> listPaginated(int pageNo, int size) {
        return null;
    }

    @Override
    public PaginationResponse<VehicleResponseDTO> listVehiclesByType(VehicleType type, int pageNo, int size) {
        return null;
    }

    @Override
    public PaginationResponse<VehicleResponseDTO> listVehiclesByEnergy(VehicleEnergyType energyType, int pageNo, int size) {
        return null;
    }

    @Override
    public PaginationResponse<VehicleResponseDTO> listVehiclesByStatus(VehicleStatus status, int pageNo, int size) {
        return null;
    }

    @Override
    public PaginationResponse<VehicleResponseDTO> searchVehicleByRsql(String query, int pageNo, int size) {
        return null;
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
