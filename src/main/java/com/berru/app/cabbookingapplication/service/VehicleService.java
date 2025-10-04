package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;

public interface VehicleService {

    VehicleResponseDTO createVehicle(NewVehicleRequestDTO newVehicleRequestDTO);

    VehicleResponseDTO getVehicleById(Integer id);

    VehicleResponseDTO getVehicleByPlate(String plate);

    PaginationResponse<VehicleResponseDTO> listPaginated(int pageNo, int size);

    PaginationResponse<VehicleResponseDTO> listVehiclesByType(VehicleType type, int pageNo, int size);

    PaginationResponse<VehicleResponseDTO> listVehiclesByEnergy(VehicleEnergyType energyType, int pageNo, int size);

    PaginationResponse<VehicleResponseDTO> listVehiclesByStatus(VehicleStatus status, int pageNo, int size);

    PaginationResponse<VehicleResponseDTO> searchVehicleByRsql(String query, int pageNo, int size);

    VehicleResponseDTO updateVehicle(Integer id, UpdateVehicleRequestDTO updateVehicleRequestDTO);

    VehicleResponseDTO updateVehicleStatus(Integer id, VehicleStatus status);

    void deleteVehicleById(Integer id);
}
