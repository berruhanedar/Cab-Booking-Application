package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;

import java.util.List;

public interface VehicleService {

    VehicleResponseDTO createVehicle(NewVehicleRequestDTO newVehicleRequestDTO);

    VehicleResponseDTO getVehicleById(Integer id);

    VehicleResponseDTO getVehicleByPlate(String plate);

    PaginationResponse<VehicleResponseDTO> listPaginated(int pageNo, int size);

    List<VehicleResponseDTO> listVehiclesByType(VehicleType type);

    List<VehicleResponseDTO> listVehiclesByEnergy(VehicleEnergyType energyType);

    List<VehicleResponseDTO> listVehiclesByStatus(VehicleStatus status);

    List<VehicleResponseDTO> searchVehicleByRsql(String query);

    VehicleResponseDTO updateVehicle(Integer id, UpdateVehicleRequestDTO updateVehicleRequestDTO);

    VehicleResponseDTO updateVehicleStatus(Integer id, VehicleStatus status);

    void deleteVehicleById(Integer id);
}
