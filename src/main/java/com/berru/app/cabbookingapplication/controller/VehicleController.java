package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewVehicleRequestDTO;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
import com.berru.app.cabbookingapplication.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
