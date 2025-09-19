package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.ContactFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewContactFormRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateContactFormRequestDTO;
import com.berru.app.cabbookingapplication.service.ContactServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/contact-form"))
public class ContactController {

    private final ContactServiceImpl contactFormServiceImpl;

    public ContactController(ContactServiceImpl contactFormServiceImpl) {
        this.contactFormServiceImpl = contactFormServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ContactFormResponseDTO> saveContactForm(@RequestBody @Valid NewContactFormRequestDTO newContactFormRequestDTO) {
        ContactFormResponseDTO contactFormResponseDTO = contactFormServiceImpl.saveContactForm(newContactFormRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactFormResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactFormResponseDTO> getContactFormById(@PathVariable Integer id) {
        ContactFormResponseDTO contactFormResponseDTO = contactFormServiceImpl.getContactFormById(id);
        return ResponseEntity.ok(contactFormResponseDTO);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<ContactFormResponseDTO>> listPaginated(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        PaginationResponse<ContactFormResponseDTO> paginationResponse = contactFormServiceImpl.listPaginated(pageNo, pageSize);
        return ResponseEntity.ok(paginationResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactFormResponseDTO> updateContactForm(@PathVariable Integer id , @RequestBody @Valid UpdateContactFormRequestDTO updateContactFormRequestDTO){
        ContactFormResponseDTO contactFormResponseDTO = contactFormServiceImpl.updateContactForm(id,updateContactFormRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(contactFormResponseDTO);
    }



    }
