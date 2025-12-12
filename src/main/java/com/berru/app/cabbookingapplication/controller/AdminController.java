package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import com.berru.app.cabbookingapplication.enums.RoleStatus;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import com.berru.app.cabbookingapplication.service.BookingService;
import com.berru.app.cabbookingapplication.service.DriverService;
import com.berru.app.cabbookingapplication.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DriverService driverService;
    private final UserService userService;
    private final BookingService bookingService;

    @GetMapping("/users")
    public ResponseEntity<?> listUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(userService.listPaginated(page, size));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Integer id,
            @RequestBody UpdateUserRequestDTO dto
    ) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @PutMapping("/users/{id}/status")
    public ResponseEntity<?> changeUserStatus(
            @PathVariable Integer id,
            @RequestParam UserStatus status
    ) {
        return ResponseEntity.ok(userService.changeUserStatus(id, status));
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<?> changeUserRole(
            @PathVariable Integer id,
            @RequestParam RoleStatus role
    ) {
        return ResponseEntity.ok(userService.changeUserRole(id, role));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/drivers")
    public ResponseEntity<?> listDrivers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(driverService.listPaginated(page, size));
    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<?> getDriver(@PathVariable Integer id) {
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<?> updateDriver(
            @PathVariable Integer id,
            @RequestBody UpdateDriverRequestDTO dto
    ) {
        return ResponseEntity.ok(driverService.updateDriver(id, dto));
    }

    @PutMapping("/drivers/{id}/availability")
    public ResponseEntity<?> updateDriverAvailability(
            @PathVariable Integer id,
            @RequestParam DriverAvailability availability
    ) {
        return ResponseEntity.ok(driverService.updateDriverAvailability(id, availability));
    }

    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriverById(id);
        return ResponseEntity.ok("Driver deleted successfully.");
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAvailableBookings());
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<?> getBookingsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @GetMapping("/bookings/driver/{driverId}")
    public ResponseEntity<?> getBookingsByDriver(@PathVariable Integer driverId) {
        return ResponseEntity.ok(bookingService.getBookingsByDriverId(driverId));
    }
}
