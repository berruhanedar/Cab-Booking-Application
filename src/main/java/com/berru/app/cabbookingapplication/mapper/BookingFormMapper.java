package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingRequestDTO;
import com.berru.app.cabbookingapplication.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingFormMapper {

    Booking toBookingForm(NewBookingRequestDTO newBookingRequestDTO);

    BookingResponseDTO toBookingFormResponseDTO(Booking booking);

}
