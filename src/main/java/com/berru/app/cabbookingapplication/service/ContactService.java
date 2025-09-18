package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;

public interface ContactService {
    ContactFormResponseDTO saveContactForm(NewContactFormRequestDTO newContactFormRequestDTO);
}
