package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateContactFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.ContactForm;
import com.berru.app.cabbookingapplication.exception.DuplicateEmailException;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.ContactFormMapper;
import com.berru.app.cabbookingapplication.repository.ContactFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public ContactFormResponseDTO getContactFormById(Integer id) {
        ContactForm contactForm = contactFormRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Contact Form not found with id "+id));
        return contactFormMapper.toContactFormResponseDTO(contactForm);
    }

    @Override
    @Transactional
    public PaginationResponse<ContactFormResponseDTO> listPaginated(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);

        Page<ContactForm> contactFormPage = contactFormRepository.findAll(pageable);

        List<ContactFormResponseDTO> contactFormResponseDTOList = contactFormPage.getContent().stream()
                .map(contactFormMapper::toContactFormResponseDTO)
                .collect(Collectors.toList());

        return PaginationResponse.<ContactFormResponseDTO>builder()
                .content(contactFormResponseDTOList)
                .pageNo(contactFormPage.getNumber())
                .pageSize(contactFormPage.getSize())
                .totalElements(contactFormPage.getTotalElements())
                .isLast(contactFormPage.isLast())
                .build();
    }

    @Override
    public ContactFormResponseDTO updateContactForm(Integer id, UpdateContactFormRequestDTO updateContactFormRequestDTO) {
        return null;
    }

    @Override
    public void deleteContactFormById(Integer id) {

    }

}
