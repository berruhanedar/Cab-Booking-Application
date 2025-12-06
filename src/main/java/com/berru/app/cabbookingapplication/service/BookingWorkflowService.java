package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.entity.Booking;

public interface BookingWorkflowService {
    BookingResponseDTO completeBookingFlow(Booking booking);
}
