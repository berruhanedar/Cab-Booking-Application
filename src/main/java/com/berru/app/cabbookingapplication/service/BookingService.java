package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;

public interface BookingService {

    BookingFormResponseDTO createBooking(NewBookingFormRequestDTO newBookingFormRequestDTO);

    BookingFormResponseDTO getAllBookings(Integer id);

    PaginationResponse<BookingFormResponseDTO> listPaginated(int pageNo, int pageSize);

}