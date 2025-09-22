package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.BookingForm;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.BookingFormMapper;
import com.berru.app.cabbookingapplication.repository.BookingFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingFormRepository bookingFormRepository;
    private final BookingFormMapper bookingFormMapper;

    public BookingServiceImpl(BookingFormRepository bookingFormRepository, BookingFormMapper bookingFormMapper) {
        this.bookingFormRepository = bookingFormRepository;
        this.bookingFormMapper = bookingFormMapper;
    }

    @Override
    @Transactional
    public BookingFormResponseDTO createBooking(NewBookingFormRequestDTO newBookingFormRequestDTO) {
        BookingForm bookingForm = bookingFormMapper.toBookingForm(newBookingFormRequestDTO);
        bookingFormRepository.save(bookingForm);
        return bookingFormMapper.toBookingFormResponseDTO(bookingForm);
    }

    @Override
    @Transactional
    public BookingFormResponseDTO getAllBookings(Integer id) {
        BookingForm bookingForm = bookingFormRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Form not found with id " + id));
        return bookingFormMapper.toBookingFormResponseDTO(bookingForm);
    }


    @Override
    @Transactional
    public PaginationResponse<BookingFormResponseDTO> listPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<BookingForm> bookingFormPage = bookingFormRepository.findAll(pageable);

        List<BookingFormResponseDTO> bookingFormResponseDTOList = bookingFormPage.getContent().stream()
                .map(bookingFormMapper::toBookingFormResponseDTO)
                .collect(Collectors.toList());

        return PaginationResponse.<BookingFormResponseDTO>builder()
                .content(bookingFormResponseDTOList)
                .pageNo(bookingFormPage.getNumber())
                .pageSize(bookingFormPage.getSize())
                .totalElements(bookingFormPage.getTotalElements())
                .isLast(bookingFormPage.isLast())
                .build();
    }

    @Override
    @Transactional
    public BookingFormResponseDTO updateBooking(Integer id, UpdateBookingFormRequestDTO updateBookingFormRequestDTO) {
        BookingForm existingBookingForm = bookingFormRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Form not found with id " + id));
        bookingFormMapper.updateBookingFormFromDTO(updateBookingFormRequestDTO, existingBookingForm);
        BookingForm bookingForm = bookingFormRepository.save(existingBookingForm);
        return bookingFormMapper.toBookingFormResponseDTO(bookingForm);
    }
}
