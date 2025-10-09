package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
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

    @GetMapping("/search")
    public ResponseEntity<List<VehicleResponseDTO>> searchVehicleByRsql(@RequestParam String query) {
        List<VehicleResponseDTO> vehicleResponseDTOs = vehicleService.searchVehicleByRsql(query);
        return ResponseEntity.ok(vehicleResponseDTOs);
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<VehicleResponseDTO>> listVehiclesByType(@PathVariable VehicleType type) {
        List<VehicleResponseDTO> vehicleResponseDTOS = vehicleService.listVehiclesByType(type);
        return ResponseEntity.ok(vehicleResponseDTOS);
    }


}
