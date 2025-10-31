package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.enums.BookingCancelledBy;
import com.berru.app.cabbookingapplication.enums.BookingStatus;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.BookingMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.BookingRepository;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.service.BookingService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@Transactional
public class BookingServiceImpl extends GenericRsqlService<Booking, BookingResponseDTO> implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final PaginationMapper paginationMapper;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper, PaginationMapper paginationMapper, DriverRepository driverRepository, UserRepository userRepository) {
        super(bookingRepository, bookingMapper::toBookingResponseDTO);
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.paginationMapper = paginationMapper;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingResponseDTO createBooking(NewBookingRequestDTO newBookingRequestDTO) {
        Driver driver = driverRepository.findById(newBookingRequestDTO.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + newBookingRequestDTO.getDriverId()));
        User user = userRepository.findById(newBookingRequestDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + newBookingRequestDTO.getUserId()));
        validateDriverAvailability(driver, newBookingRequestDTO.getDate(), newBookingRequestDTO.getTime());
        Booking booking = bookingMapper.toBooking(newBookingRequestDTO);
        booking.setUser(user);
        booking.setDriver(driver);
        booking.setStatus(BookingStatus.PENDING);
        Booking bookingResult = bookingRepository.save(booking);
        return bookingMapper.toBookingResponseDTO(bookingResult);

    }

    @Override
    public List<BookingResponseDTO> getAvailableBookings() {
        List<Booking> bookings = bookingRepository.findByStatus(BookingStatus.PENDING);
        return bookings.stream().map(bookingMapper::toBookingResponseDTO)
                .toList();

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

    private void validateDriverAvailability(Driver driver, LocalDate date, LocalTime time) {
        boolean isDriverAvailable = bookingRepository.existsByDriverAndDateAndTimeAndStatusNot(
                driver, date, time, BookingStatus.CANCELLED);

        if (isDriverAvailable) {
            throw new IllegalStateException("Driver is not available at the requested time");
        }
    }
}
