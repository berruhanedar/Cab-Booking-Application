package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.DriverResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewDriverRequestDTO;
import com.berru.app.cabbookingapplication.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@RequestBody @Valid NewDriverRequestDTO newDriverRequestDTO) {
        DriverResponseDTO driverResponseDTO = driverService.createDriver(newDriverRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(driverResponseDTO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DriverResponseDTO> getDriverById(@PathVariable Integer id) {
        DriverResponseDTO driverResponseDTO = driverService.getDriverById(id);
        return ResponseEntity.ok().body(driverResponseDTO);
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<DriverResponseDTO> getDriverByUserId(@PathVariable Integer userId) {
        DriverResponseDTO driverResponseDTO = driverService.getDriverByUserId(userId);
        return ResponseEntity.ok().body(driverResponseDTO);
    }
}
