package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;

public interface BookingService {
   BookingFormResponseDTO createBooking(NewBookingFormRequestDTO newBookingFormRequestDTO);
}