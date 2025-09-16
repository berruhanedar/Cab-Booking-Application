package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.service.ContactFormServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/contact-form"))
public class ContactFormController {

    private final ContactFormServiceImpl contactFormServiceImpl;

    public ContactFormController(ContactFormServiceImpl contactFormServiceImpl) {
        this.contactFormServiceImpl = contactFormServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ContactFormResponseDTO> saveContactForm(@RequestBody @Valid NewContactFormRequestDTO newContactFormRequestDTO) {
        ContactFormResponseDTO contactFormResponseDTO = contactFormServiceImpl.saveContactForm(newContactFormRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactFormResponseDTO);
    }
}
