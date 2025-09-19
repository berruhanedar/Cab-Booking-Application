package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.UpdateContactFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.ContactForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactFormMapper {

    ContactForm toContactForm(NewContactFormRequestDTO newContactFormRequestDTO);

    ContactFormResponseDTO toContactFormResponseDTO(ContactForm contactForm);

    void updateContactFormFromDTO(UpdateContactFormRequestDTO updateContactFormRequestDTO,@MappingTarget ContactForm contactForm);

}
