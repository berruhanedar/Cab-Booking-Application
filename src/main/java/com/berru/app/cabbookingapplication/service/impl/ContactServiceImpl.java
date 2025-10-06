package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateContactFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.ContactForm;
import com.berru.app.cabbookingapplication.exception.DuplicateEmailException;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.ContactFormMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.ContactFormRepository;
import com.berru.app.cabbookingapplication.rsql.CustomRsqlVisitor;
import com.berru.app.cabbookingapplication.service.ContactService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactFormRepository contactFormRepository;
    private final ContactFormMapper contactFormMapper;
    private final PaginationMapper paginationMapper;

    public ContactServiceImpl(ContactFormRepository contactFormRepository, ContactFormMapper contactFormMapper, PaginationMapper paginationMapper) {
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
    @Transactional
    public List<ContactFormResponseDTO> searchContactFormByRsql(String query) {
        RSQLParser parser = new RSQLParser();
        Node rootNode = parser.parse(query);

        CustomRsqlVisitor<ContactForm> visitor = new CustomRsqlVisitor<>();
        Specification<ContactForm> spec = rootNode.accept(visitor);


        List<ContactForm> contactForm = contactFormRepository.findAll(spec);

        return contactForm.stream().map(contactFormMapper::toContactFormResponseDTO).collect(Collectors.toList());
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
