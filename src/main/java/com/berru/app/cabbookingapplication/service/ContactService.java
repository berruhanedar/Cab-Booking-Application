package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateContactFormRequestDTO;

public interface ContactService {
    ContactFormResponseDTO saveContactForm(NewContactFormRequestDTO newContactFormRequestDTO);

    ContactFormResponseDTO getContactFormById(Integer id);

    PaginationResponse<ContactFormResponseDTO> listPaginated(int pageNo, int size);

    ContactFormResponseDTO updateContactForm(Integer id, UpdateContactFormRequestDTO updateContactFormRequestDTO);

    void deleteContactFormById(Integer id);

}
