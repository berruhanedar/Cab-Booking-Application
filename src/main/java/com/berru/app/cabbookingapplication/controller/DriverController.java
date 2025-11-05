package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import com.berru.app.cabbookingapplication.service.BookingService;
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
    private final BookingService bookingService;

    public DriverController(DriverService driverService, BookingService bookingService) {
        this.driverService = driverService;
        this.bookingService = bookingService;
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

    @PutMapping("/update/driver/{driverId}")
    public ResponseEntity<DriverResponseDTO> updateDriver(@PathVariable Integer driverId, @RequestBody @Valid UpdateDriverRequestDTO updateDriverRequestDTO) {
        DriverResponseDTO driverResponseDTO = driverService.updateDriver(driverId, updateDriverRequestDTO);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @PutMapping("/{driverId}/location")
    public ResponseEntity<DriverResponseDTO> updateDriverLocation(@PathVariable Integer driverId, @RequestBody @Valid UpdateLocationRequestDTO locationUpdate) {
        DriverResponseDTO driverResponseDTO = driverService.updateDriverLocation(driverId, locationUpdate);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @PutMapping("/{driverId}/availability/{availability}")
    public ResponseEntity<DriverResponseDTO> updateDriverAvailability(@PathVariable Integer driverId, @PathVariable DriverAvailability availability) {
        DriverResponseDTO driverResponseDTO = driverService.updateDriverAvailability(driverId, availability);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @PostMapping("/{driverId}/vehicles/{vehicleId}")
    public ResponseEntity<DriverResponseDTO> addVehicleToDriver(@PathVariable Integer driverId, @PathVariable Integer vehicleId) {
        DriverResponseDTO driverResponseDTO = driverService.addVehicleToDriver(driverId, vehicleId);
        return ResponseEntity.ok(driverResponseDTO);
    }

    @DeleteMapping("/{driverId}/vehicles/{vehicleId}")
    public ResponseEntity<Void> removeVehicleFromDriver(@PathVariable Integer driverId, @PathVariable Integer vehicleId) {
        driverService.removeVehicleFromDriver(driverId, vehicleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer driverId) {
        driverService.deleteDriverById(driverId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/available-bookings")
    public ResponseEntity<List<BookingResponseDTO>> getAvailableBookings() {
        List<BookingResponseDTO> availableBookings = bookingService.getAvailableBookings();
        return ResponseEntity.ok(availableBookings);
    }

    @PutMapping("/bookings/{bookingId}/accept")
    public ResponseEntity<BookingResponseDTO> acceptBooking(
            @PathVariable Integer bookingId,
            @RequestParam Integer driverId
    ) {
        BookingResponseDTO response = bookingService.acceptBooking(bookingId, driverId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/bookings/{bookingId}/complete")
    public ResponseEntity<BookingResponseDTO> completeBooking(@PathVariable Integer bookingId) {
        BookingResponseDTO response = bookingService.completeBooking(bookingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{driverId}/average-rating")
    public ResponseEntity<Double> getAverageRatingByDriverId(@PathVariable Integer driverId) {
        Double averageRating = driverService.getAverageRatingByDriverId(driverId);
        return ResponseEntity.ok(averageRating);
    }
}