package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.ContactForm;
import com.berru.app.cabbookingapplication.exception.DuplicateEmailException;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.ContactFormMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.ContactFormRepository;
import com.berru.app.cabbookingapplication.service.ContactService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl extends GenericRsqlService<ContactForm, ContactFormResponseDTO> implements ContactService {

    private final ContactFormRepository contactFormRepository;
    private final ContactFormMapper contactFormMapper;
    private final PaginationMapper paginationMapper;

    public ContactServiceImpl(ContactFormRepository contactFormRepository,
                              ContactFormMapper contactFormMapper,
                              PaginationMapper paginationMapper) {
        super(contactFormRepository, contactFormMapper::toContactFormResponseDTO);
        this.contactFormRepository = contactFormRepository;
        this.contactFormMapper = contactFormMapper;
        this.paginationMapper = paginationMapper;
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

    @Override
    @Transactional
    public ContactFormResponseDTO getContactFormById(Integer id) {
        ContactForm contactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Form not found with id " + id));
        return contactFormMapper.toContactFormResponseDTO(contactForm);
    }

    @Override
    @Transactional
    public PaginationResponse<ContactFormResponseDTO> listPaginated(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<ContactForm> contactFormPage = contactFormRepository.findAll(pageable);

        return paginationMapper.toPaginationResponse(contactFormPage, contactFormMapper::toContactFormResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactFormResponseDTO> searchContactFormByRsql(String query) {
        return searchByRsql(query);
    }

    @Override
    @Transactional
    public ContactFormResponseDTO updateContactForm(Integer id, UpdateContactFormRequestDTO updateContactFormRequestDTO) {
        ContactForm existingContactForm = contactFormRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact Form not found with id " + id));
        contactFormMapper.updateContactFormFromDTO(updateContactFormRequestDTO, existingContactForm);
        ContactForm savedContactForm = contactFormRepository.save(existingContactForm);
        return contactFormMapper.toContactFormResponseDTO(savedContactForm);
    }

    @Override
    @Transactional
    public void deleteContactFormById(Integer id) {
        ContactForm contactForm = contactFormRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact Form not found with id " + id));
        contactFormRepository.delete(contactForm);
    }
}
