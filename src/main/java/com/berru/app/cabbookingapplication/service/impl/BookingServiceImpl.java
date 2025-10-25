package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.BookingForm;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.BookingFormMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.BookingFormRepository;
import com.berru.app.cabbookingapplication.service.BookingService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingServiceImpl extends GenericRsqlService<BookingForm, BookingFormResponseDTO> implements BookingService {

    private final BookingFormRepository bookingFormRepository;
    private final BookingFormMapper bookingFormMapper;
    private final PaginationMapper paginationMapper;

    public BookingServiceImpl(BookingFormRepository bookingFormRepository, BookingFormMapper bookingFormMapper, PaginationMapper paginationMapper) {
        super(bookingFormRepository, bookingFormMapper::toBookingFormResponseDTO);
        this.bookingFormRepository = bookingFormRepository;
        this.bookingFormMapper = bookingFormMapper;
        this.paginationMapper = paginationMapper;
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

        return paginationMapper.toPaginationResponse(bookingFormPage, bookingFormMapper::toBookingFormResponseDTO);
    }

    @Override
    public List<BookingFormResponseDTO> searchBookingByRsql(String query) {
        return searchByRsql(query);
    }

    @Override
    @Transactional
    public BookingFormResponseDTO updateBooking(Integer id, UpdateBookingFormRequestDTO updateBookingFormRequestDTO) {
        BookingForm existingBookingForm = bookingFormRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Form not found with id " + id));
        bookingFormMapper.updateBookingFormFromDTO(updateBookingFormRequestDTO, existingBookingForm);
        BookingForm bookingForm = bookingFormRepository.save(existingBookingForm);
        return bookingFormMapper.toBookingFormResponseDTO(bookingForm);
    }

    @Override
    @Transactional
    public void deleteBookingById(Integer id) {
        BookingForm bookingForm = bookingFormRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Form not found with id " + id));
        bookingFormRepository.delete(bookingForm);
    }

    @Override
    public void completeBooking(Integer bookingId) {

    }

    @Override
    public BookingFormResponseDTO completeBookingWithRating(Integer bookingId, Double ratingValue, Double tipAmount, String feedback) {
        return null;
    }
}
