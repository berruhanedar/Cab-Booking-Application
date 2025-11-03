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

    @PutMapping("/{bookingId}/accept/{driverId}")
    public ResponseEntity<BookingResponseDTO> acceptBooking(@PathVariable Integer bookingId,
                                                            @PathVariable Integer driverId) {
        BookingResponseDTO bookingResponseDTO = bookingService.acceptBooking(bookingId, driverId);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @PutMapping("/{bookingId}/complete")
    public ResponseEntity<BookingResponseDTO> completeBooking(@PathVariable Integer bookingId) {
        BookingResponseDTO bookingResponseDTO = bookingService.completeBooking(bookingId);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable Integer userId) {
        List<BookingResponseDTO> bookingResponseDTOs = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookingResponseDTOs);
    }

    @GetMapping("/driver/{driverId}")
    public  ResponseEntity<List<BookingResponseDTO>> getBookingsByDriverId(@PathVariable Integer driverId) {
        List<BookingResponseDTO> bookingResponseDTOs = bookingService.getBookingsByDriverId(driverId);
        return ResponseEntity.ok(bookingResponseDTOs);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Integer bookingId) {
       BookingResponseDTO bookingResponseDTO = bookingService.getBookingById(bookingId);
       return ResponseEntity.ok(bookingResponseDTO);
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