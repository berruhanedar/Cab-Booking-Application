package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
import com.berru.app.cabbookingapplication.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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





}
