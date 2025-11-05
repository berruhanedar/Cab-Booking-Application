package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingRequestDTO;
import com.berru.app.cabbookingapplication.enums.CancelledBy;

import java.util.List;

public interface BookingService {

    BookingResponseDTO createBooking(NewBookingRequestDTO newBookingRequestDTO);

    List<BookingResponseDTO> getAvailableBookings();

    BookingResponseDTO acceptBooking(Integer bookingId, Integer driverId);

    BookingResponseDTO completeBooking(Integer bookingId);

    List<BookingResponseDTO> getBookingsByUserId(Integer userId);

    List<BookingResponseDTO> getBookingsByDriverId(Integer driverId);

    BookingResponseDTO getBookingById(Integer bookingId);

    BookingResponseDTO cancelBooking(Integer bookingId, Integer userId, CancelledBy cancelledBy);

}
