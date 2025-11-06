package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.enums.*;
import com.berru.app.cabbookingapplication.exception.*;
import com.berru.app.cabbookingapplication.mapper.BookingMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.BookingRepository;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.service.BookingService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class BookingServiceImpl extends GenericRsqlService<Booking, BookingResponseDTO> implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final PaginationMapper paginationMapper;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final BookingWorkflowService bookingWorkflowService;
    private final BookingValidationService bookingValidationService;

    public BookingServiceImpl(
            BookingRepository bookingRepository, BookingWorkflowService bookingWorkflowService, BookingMapper bookingMapper, PaginationMapper paginationMapper, DriverRepository driverRepository, UserRepository userRepository, BookingValidationService bookingValidationService) {
        super(bookingRepository, bookingMapper::toBookingResponseDTO);
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.paginationMapper = paginationMapper;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.bookingWorkflowService = bookingWorkflowService;
        this.bookingValidationService = bookingValidationService;
    }

    @Override
    public BookingResponseDTO createBooking(NewBookingRequestDTO newBookingRequestDTO) {
        Driver driver = driverRepository.findById(newBookingRequestDTO.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + newBookingRequestDTO.getDriverId()));
        User user = userRepository.findById(newBookingRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + newBookingRequestDTO.getUserId()));
        bookingValidationService.validateDriverAvailability(driver, newBookingRequestDTO.getDate(), newBookingRequestDTO.getTime());
        Booking booking = bookingMapper.toBooking(newBookingRequestDTO);
        booking.setUser(user);
        booking.setDriver(driver);
        booking.setStatus(BookingStatus.PENDING);
        Booking bookingResult = bookingRepository.save(booking);
        return bookingMapper.toBookingResponseDTO(bookingResult);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAvailableBookings() {
        return bookingRepository.findByStatus(BookingStatus.PENDING)
                .stream()
                .map(bookingMapper::toBookingResponseDTO)
                .toList();
    }

    @Override
    public BookingResponseDTO acceptBooking(Integer bookingId, Integer driverId) {
        Booking booking = getBookingByIdEntity(bookingId);
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + driverId));
        if (booking.getDriver() != null) {
            throw new BookingAlreadyConfirmedException("Booking already assigned to a driver.");
        }
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setDriver(driver);
        return bookingMapper.toBookingResponseDTO(bookingRepository.save(booking));
    }

    @Override
    @Transactional
    public BookingResponseDTO completeBooking(Integer bookingId) {
        Booking booking = getBookingByIdEntity(bookingId);
        return bookingWorkflowService.completeBookingFlow(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getBookingsByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream().map(bookingMapper::toBookingResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getBookingsByDriverId(Integer driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + driverId));
        List<Booking> bookings = bookingRepository.findByDriverId(driverId);
        return bookings.stream().map(bookingMapper::toBookingResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponseDTO getBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));
        return bookingMapper.toBookingResponseDTO(booking);
    }

    @Override
    public BookingResponseDTO cancelBooking(Integer bookingId, Integer userId, CancelledBy cancelledBy) {
        Booking booking = getBookingByIdEntity(bookingId);
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException("Booking is already cancelled.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        validateCancellationPermission(booking, user, cancelledBy);
        booking.setStatus(BookingStatus.CANCELLED);
        booking.setCancelledBy(cancelledBy);
        booking.setCancelledAt(LocalDateTime.now());
        return bookingMapper.toBookingResponseDTO(bookingRepository.save(booking));
    }

    private Booking getBookingByIdEntity(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));
    }

    private void validateCancellationPermission(Booking booking, User user, CancelledBy cancelledBy) {
        boolean hasPermission = switch (cancelledBy) {
            case CUSTOMER -> booking.getUser().getId().equals(user.getId());
            case DRIVER -> booking.getDriver() != null &&
                    booking.getDriver().getUser().getId().equals(user.getId());
            case ADMIN -> user.getRole().equals(RoleStatus.ADMIN);
            default -> false;
        };

        if (!hasPermission) {
            throw new UnauthorizedException("Unauthorized cancellation attempt");
        }
    }
}
