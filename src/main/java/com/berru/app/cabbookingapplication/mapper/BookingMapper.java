package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.ContactForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    ContactForm toContactForm(NewContactFormRequestDTO newContactFormRequestDTO);

}
