package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingRequestDTO;
import com.berru.app.cabbookingapplication.enums.BookingCancelledBy;

import java.util.List;

public interface BookingService {

    /**
     * 1️⃣ Kullanıcı yeni bir booking oluşturur.
     * Status = PENDING olur.
     *
     * @param newBookingRequestDTO Booking oluşturmak için gerekli bilgiler.
     * @return Oluşturulan booking bilgilerini içeren DTO.
     */
    BookingResponseDTO createBooking(NewBookingRequestDTO newBookingRequestDTO);

    /**
     * 2️⃣ Driver, henüz sürücü atanmamış (PENDING) booking’leri listeler.
     *
     * @return PENDING statüsündeki tüm bookingleri içeren DTO listesi.
     */
    List<BookingResponseDTO> getAvailableBookings();

    /**
     * 3️⃣ Driver belirli bir booking’i kabul eder.
     * Booking’e driver atanır ve status CONFIRMED olur.
     *
     * @param bookingId Kabul edilecek booking ID.
     * @param driverId Booking’i kabul eden driver ID.
     * @return Güncellenmiş booking bilgilerini içeren DTO.
     */
    BookingResponseDTO acceptBooking(Integer bookingId, Integer driverId);

    /**
     * 4️⃣ Booking tamamlandığında çağrılır.
     * Status COMPLETED olur, driver'ın totalRides alanı güncellenir.
     *
     * @param bookingId Tamamlanacak booking ID.
     * @return Güncellenmiş booking bilgilerini içeren DTO.
     */
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
