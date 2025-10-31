package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingRequestDTO;
import com.berru.app.cabbookingapplication.enums.BookingCancelledBy;
import com.berru.app.cabbookingapplication.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody @Valid NewBookingRequestDTO newBookingRequestDTO) {
        BookingResponseDTO bookingResponseDTO = bookingService.createBooking(newBookingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponseDTO);
    }

    @GetMapping("/available")
    public List<BookingResponseDTO> getAvailableBookings() {
        return bookingService.getAvailableBookings();
    }

    @PostMapping("/{bookingId}/accept/{driverId}")
    public BookingResponseDTO acceptBooking(@PathVariable Integer bookingId,
                                            @PathVariable Integer driverId) {
        return bookingService.acceptBooking(bookingId, driverId);
    }

    @PostMapping("/{bookingId}/complete")
    public BookingResponseDTO completeBooking(@PathVariable Integer bookingId) {
        return bookingService.completeBooking(bookingId);
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponseDTO> getBookingsByUserId(@PathVariable Integer userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    @GetMapping("/driver/{driverId}")
    public List<BookingResponseDTO> getBookingsByDriverId(@PathVariable Integer driverId) {
        return bookingService.getBookingsByDriverId(driverId);
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDTO getBookingById(@PathVariable Integer bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(
            @PathVariable Integer bookingId,
            @RequestParam("cancelledBy") BookingCancelledBy cancelledBy
    ) {
        BookingResponseDTO response = bookingService.cancelBooking(bookingId, cancelledBy);
        return ResponseEntity.ok(response);
    }

}