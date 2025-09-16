package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.ContactForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactFormMapper {

    ContactForm toContactForm(NewContactFormRequestDTO newContactFormRequestDTO);

    ContactFormResponseDTO toContactFormResponseDTO(ContactForm contactForm);

}
