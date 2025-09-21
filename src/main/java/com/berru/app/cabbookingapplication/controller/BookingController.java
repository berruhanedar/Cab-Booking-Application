package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.service.BookingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingServiceImpl bookingServiceImpl;

    public BookingController(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BookingFormResponseDTO> createBooking(NewBookingFormRequestDTO newBookingFormRequestDTO) {
        BookingFormResponseDTO bookingFormResponseDTO = bookingServiceImpl.createBooking(newBookingFormRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingFormResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingFormResponseDTO> getByIdBooking(@PathVariable Integer id) {
        BookingFormResponseDTO bookingFormResponseDTO = bookingServiceImpl.getAllBookings(id);
        return ResponseEntity.ok(bookingFormResponseDTO);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<BookingFormResponseDTO>> listPaginated(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PaginationResponse<BookingFormResponseDTO> paginationResponse = bookingServiceImpl.listPaginated(pageNo, pageSize);
        return ResponseEntity.ok(paginationResponse);
    }

}