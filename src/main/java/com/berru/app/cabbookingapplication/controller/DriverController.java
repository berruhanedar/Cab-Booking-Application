package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.DriverResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewDriverRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.VehicleResponseDTO;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import com.berru.app.cabbookingapplication.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
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

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> getDriverById(@PathVariable Integer id) {
        DriverResponseDTO driverResponseDTO = driverService.getDriverById(id);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<DriverResponseDTO> getDriverByUserId(@PathVariable Integer userId) {
        DriverResponseDTO driverResponseDTO = driverService.getDriverByUserId(userId);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<DriverResponseDTO>> listPaginated(@RequestParam(defaultValue = "0") int pageNo,
                                                                               @RequestParam(defaultValue = "10") int size) {
        PaginationResponse<DriverResponseDTO> paginationResponse = driverService.listPaginated(pageNo, size);
        return ResponseEntity.ok(paginationResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DriverResponseDTO>> searchDriverByRsql(@RequestParam String query) {
        List<DriverResponseDTO> driverResponseDTO = driverService.searchDriverByRsql(query);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @GetMapping("/rating")
    public ResponseEntity<List<DriverResponseDTO>> findDriversByRating(@RequestParam(name = "minRating") Double minRating) {
        List<DriverResponseDTO> driverResponseDTO = driverService.findDriversByRating(minRating);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @GetMapping("/search/availability/{availability}")
    public ResponseEntity<List<DriverResponseDTO>> findDriversByAvailability(@PathVariable DriverAvailability availability) {
        List<DriverResponseDTO> driverResponseDTO = driverService.findDriversByAvailability(availability);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @GetMapping("{driverId}/vehicles")
    public ResponseEntity<List<VehicleResponseDTO>> getVehiclesByDriverId(@PathVariable Integer driverId) {
        List<VehicleResponseDTO> vehicleResponseDTO = driverService.getVehiclesByDriverId(driverId);
        return ResponseEntity.ok(vehicleResponseDTO);
    }
}
