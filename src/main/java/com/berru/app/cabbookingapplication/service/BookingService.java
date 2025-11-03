package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingRequestDTO;
import com.berru.app.cabbookingapplication.enums.BookingCancelledBy;

import java.util.List;

public interface BookingService {

    BookingResponseDTO createBooking(NewBookingRequestDTO newBookingRequestDTO);

    List<BookingResponseDTO> getAvailableBookings();

    BookingResponseDTO acceptBooking(Integer bookingId, Integer driverId);

    BookingResponseDTO completeBooking(Integer bookingId);

    List<BookingResponseDTO> getBookingsByUserId(Integer userId);

    List<BookingResponseDTO> getBookingsByDriverId(Integer driverId);

    BookingResponseDTO getBookingById(Integer bookingId);

    /**
     * 8️⃣ Kullanıcı veya driver booking'i iptal edebilir.
     * Status CANCELLED olur.
     *
     * @param bookingId İptal edilecek booking ID.
     * @param cancelledBy "USER" veya "DRIVER" bilgisini içerir.
     * @return Güncellenmiş booking bilgilerini içeren DTO.
     */
    BookingResponseDTO cancelBooking(Integer bookingId, BookingCancelledBy cancelledBy);

}
