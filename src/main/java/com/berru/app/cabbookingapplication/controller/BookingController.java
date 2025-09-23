package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.service.BookingServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<BookingFormResponseDTO> createBooking(@RequestBody @Valid NewBookingFormRequestDTO newBookingFormRequestDTO) {
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

    @GetMapping("/search")
    public ResponseEntity<List<BookingFormResponseDTO>> search(@RequestParam String query) {
        List<BookingFormResponseDTO> bookingFormResponseDTOList = bookingServiceImpl.searchBookingByRsql(query);
        return ResponseEntity.ok(bookingFormResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingFormResponseDTO> updateBooking(@PathVariable Integer id , @RequestBody UpdateBookingFormRequestDTO updateBookingFormRequestDTO) {
        BookingFormResponseDTO bookingFormResponseDTO = bookingServiceImpl.updateBooking(id,updateBookingFormRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(bookingFormResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        bookingServiceImpl.deleteBookingById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}