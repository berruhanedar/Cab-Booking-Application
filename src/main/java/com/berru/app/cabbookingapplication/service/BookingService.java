package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateBookingFormRequestDTO;

import java.awt.print.Book;

public interface BookingService {

    BookingFormResponseDTO createBooking(NewBookingFormRequestDTO newBookingFormRequestDTO);

    BookingFormResponseDTO getAllBookings(Integer id);

    PaginationResponse<BookingFormResponseDTO> listPaginated(int pageNo, int pageSize);

    BookingFormResponseDTO updateBooking(Integer id , UpdateBookingFormRequestDTO updateBookingFormRequestDTO);

}