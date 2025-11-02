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

    /**
     * 5️⃣ Belirli bir kullanıcıya ait tüm booking’leri döndürür.
     *
     * @param userId Kullanıcı ID.
     * @return Kullanıcının tüm bookinglerini içeren DTO listesi.
     */
    List<BookingResponseDTO> getBookingsByUserId(Integer userId);

    /**
     * 6️⃣ Belirli bir sürücüye ait tüm booking’leri döndürür.
     *
     * @param driverId Driver ID.
     * @return Sürücünün tüm bookinglerini içeren DTO listesi.
     */
    List<BookingResponseDTO> getBookingsByDriverId(Integer driverId);

    /**
     * 7️⃣ Tek bir booking'i ID'ye göre getirir.
     *
     * @param bookingId Booking ID.
     * @return Booking bilgilerini içeren DTO.
     */
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
