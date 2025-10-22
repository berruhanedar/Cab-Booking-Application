package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateBookingFormRequestDTO;

import java.util.List;

public interface BookingService {

    BookingFormResponseDTO createBooking(NewBookingFormRequestDTO newBookingFormRequestDTO);

    BookingFormResponseDTO getAllBookings(Integer id);

    PaginationResponse<BookingFormResponseDTO> listPaginated(int pageNo, int pageSize);

    List<BookingFormResponseDTO> searchBookingByRsql(String query);

    BookingFormResponseDTO updateBooking(Integer id, UpdateBookingFormRequestDTO updateBookingFormRequestDTO);

    void deleteBookingById(Integer id);

    /**
     * Booking tamamlandığında çağrılır.
     * Driver'ın totalRides ve rating alanlarını günceller.
     */
    void completeBooking(Integer bookingId);

    /**
     * Booking tamamlandığında eğer kullanıcı tip (bahşiş) ve rating verirse,
     * RatingService ve PaymentService çağrılarak işlem yapılır.
     */
    BookingFormResponseDTO completeBookingWithRating(Integer bookingId, Double ratingValue, Double tipAmount, String feedback);
}