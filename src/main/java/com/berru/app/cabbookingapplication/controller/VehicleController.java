package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.berru.app.cabbookingapplication.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody @Valid NewVehicleRequestDTO newVehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.createVehicle(newVehicleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Integer id) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.getVehicleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponseDTO);

    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<VehicleResponseDTO> getVehicleByPlate(@PathVariable String plate) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.getVehicleByPlate(plate);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponseDTO);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<VehicleResponseDTO>> listPaginated(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(vehicleService.listPaginated(pageNo, size));
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<VehicleResponseDTO>> listVehiclesByType(@PathVariable VehicleType type) {
        List<VehicleResponseDTO> vehicleResponseDTOS = vehicleService.listVehiclesByType(type);
        return ResponseEntity.ok(vehicleResponseDTOS);
    }

    @GetMapping("/search/energy/{energyType}")
    public ResponseEntity<List<VehicleResponseDTO>> listVehiclesByEnergy(@PathVariable VehicleEnergyType energyType) {
        List<VehicleResponseDTO> vehicles = vehicleService.listVehiclesByEnergy(energyType);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<VehicleResponseDTO>> listVehiclesByStatus(@PathVariable VehicleStatus status) {
        List<VehicleResponseDTO> vehicles = vehicleService.listVehiclesByStatus(status);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VehicleResponseDTO>> searchVehicleByRsql(@RequestParam String query) {
        List<VehicleResponseDTO> vehicleResponseDTOs = vehicleService.searchVehicleByRsql(query);
        return ResponseEntity.ok(vehicleResponseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable Integer id, @RequestBody @Valid UpdateVehicleRequestDTO updateVehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.updateVehicle(id, updateVehicleRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponseDTO);
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<VehicleResponseDTO> updateVehicleStatus(@PathVariable Integer id, @PathVariable VehicleStatus status) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.updateVehicleStatus(id, status);
        return ResponseEntity.ok(vehicleResponseDTO);
    }


}
