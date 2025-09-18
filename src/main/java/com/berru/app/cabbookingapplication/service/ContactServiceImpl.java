package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.ContactForm;
import com.berru.app.cabbookingapplication.exception.DuplicateEmailException;
import com.berru.app.cabbookingapplication.mapper.ContactFormMapper;
import com.berru.app.cabbookingapplication.repository.ContactFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactFormRepository contactFormRepository;
    private final ContactFormMapper contactFormMapper;

    public ContactServiceImpl(ContactFormRepository contactFormRepository, ContactFormMapper contactFormMapper) {
        this.contactFormRepository = contactFormRepository;
        this.contactFormMapper = contactFormMapper;
    }

    @Override
    @Transactional
    public ContactFormResponseDTO saveContactForm(NewContactFormRequestDTO newContactFormRequestDTO) {
        ContactForm contactForm = contactFormMapper.toContactForm(newContactFormRequestDTO);
        contactFormRepository.findByEmail(contactForm.getEmail())
                .ifPresent(cf -> {
                    throw new DuplicateEmailException("Email already used");
                });
        ContactForm savedContactForm = contactFormRepository.save(contactForm);
        return contactFormMapper.toContactFormResponseDTO(savedContactForm);
    }
}
