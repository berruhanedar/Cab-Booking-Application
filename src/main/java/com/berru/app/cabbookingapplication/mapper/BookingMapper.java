package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingRequestDTO;
import com.berru.app.cabbookingapplication.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toBooking(NewBookingRequestDTO newBookingRequestDTO);

    BookingResponseDTO toBookingResponseDTO(Booking booking);

}
