package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateContactFormRequestDTO;

import java.util.List;

public interface ContactService {
    ContactFormResponseDTO saveContactForm(NewContactFormRequestDTO newContactFormRequestDTO);

    ContactFormResponseDTO getContactFormById(Integer id);

    PaginationResponse<ContactFormResponseDTO> listPaginated(int pageNo, int size);

    List<ContactFormResponseDTO> searchContactFormByRsql(String query);

    ContactFormResponseDTO updateContactForm(Integer id, UpdateContactFormRequestDTO updateContactFormRequestDTO);

    void deleteContactFormById(Integer id);

}
