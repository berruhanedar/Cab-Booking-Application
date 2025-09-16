package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;

public interface ContactFormService {
    public ContactFormResponseDTO saveContactForm(NewContactFormRequestDTO newContactFormRequestDTO);
}
