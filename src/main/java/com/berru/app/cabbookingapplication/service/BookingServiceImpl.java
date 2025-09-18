package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.BookingFormResponseDTO;
import com.berru.app.cabbookingapplication.dto.NewBookingFormRequestDTO;
import com.berru.app.cabbookingapplication.entity.BookingForm;
import com.berru.app.cabbookingapplication.mapper.BookingFormMapper;
import com.berru.app.cabbookingapplication.repository.BookingFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingFormRepository bookingFormRepository;
    private final BookingFormMapper bookingFormMapper;

    public BookingServiceImpl(BookingFormRepository bookingFormRepository, BookingFormMapper bookingFormMapper) {
        this.bookingFormRepository = bookingFormRepository;
        this.bookingFormMapper = bookingFormMapper;
    }

    @Override
    @Transactional
    public BookingFormResponseDTO createBooking(NewBookingFormRequestDTO  newBookingFormRequestDTO) {
        BookingForm bookingForm = bookingFormMapper.toBookingForm(newBookingFormRequestDTO);
        bookingFormRepository.save(bookingForm);
        return bookingFormMapper.toBookingFormResponseDTO(bookingForm);
    }
}