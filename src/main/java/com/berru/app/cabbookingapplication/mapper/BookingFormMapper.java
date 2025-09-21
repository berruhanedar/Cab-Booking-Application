package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.UpdateBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.BookingForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingFormMapper {

    BookingForm toBookingForm(NewBookingFormRequestDTO newBookingFormRequestDTO);

    BookingFormResponseDTO toBookingFormResponseDTO(BookingForm bookingForm);

    void updateBookingFormFromDTO(UpdateBookingFormRequestDTO updateBookingFormRequestDTO, @MappingTarget BookingForm bookingForm);

}
