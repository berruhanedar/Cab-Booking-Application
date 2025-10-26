package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.enums.BookingCancelledBy;
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
public class BookingServiceImpl extends GenericRsqlService<Booking, BookingResponseDTO> implements BookingService {

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
    public BookingResponseDTO createBooking(NewBookingRequestDTO newBookingRequestDTO) {
        return null;
    }

    @Override
    public List<BookingResponseDTO> getAvailableBookings() {
        return List.of();
    }

    @Override
    public BookingResponseDTO acceptBooking(Integer bookingId, Integer driverId) {
        return null;
    }

    @Override
    public BookingResponseDTO completeBooking(Integer bookingId) {
        return null;
    }

    @Override
    public List<BookingResponseDTO> getBookingsByUserId(Integer userId) {
        return List.of();
    }

    @Override
    public List<BookingResponseDTO> getBookingsByDriverId(Integer driverId) {
        return List.of();
    }

    @Override
    public BookingResponseDTO getBookingById(Integer bookingId) {
        return null;
    }

    @Override
    public BookingResponseDTO cancelBooking(Integer bookingId, BookingCancelledBy cancelledBy) {
        return null;
    }
}
