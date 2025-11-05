package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.enums.BookingStatus;
import com.berru.app.cabbookingapplication.enums.CancelledBy;
import com.berru.app.cabbookingapplication.enums.RoleStatus;
import com.berru.app.cabbookingapplication.exception.*;
import com.berru.app.cabbookingapplication.mapper.BookingMapper;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.repository.BookingRepository;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.service.BookingService;
import com.berru.app.cabbookingapplication.service.RatingService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final RatingService ratingService;

    private static final String BOOKING_NOT_FOUND = "Booking not found with id ";
    private static final String USER_NOT_FOUND = "User not found with id ";
    private static final String DRIVER_NOT_FOUND = "Driver not found with id ";

    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper, PaginationMapper paginationMapper, DriverRepository driverRepository, UserRepository userRepository,RatingService ratingService) {
        super(bookingRepository, bookingMapper::toBookingResponseDTO);
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.paginationMapper = paginationMapper;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.ratingService = ratingService;
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
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAvailableBookings() {
        List<Booking> bookings = bookingRepository.findByStatus(BookingStatus.PENDING);
        return bookings.stream().map(bookingMapper::toBookingResponseDTO)
                .toList();
    }

    @Override
    public BookingResponseDTO acceptBooking(Integer bookingId, Integer driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + driverId));
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));
        if (booking.getDriver() != null) {
            throw new BookingAlreadyConfirmedException("Booking already assigned to a driver.");
        }
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setDriver(driver);
        Booking updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toBookingResponseDTO(updatedBooking);
    }

    @Override
    public BookingResponseDTO completeBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));
        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new InvalidBookingStateException("Only confirmed bookings can be completed.");
        }
        booking.setStatus(BookingStatus.COMPLETED);
        Booking updatedBooking = bookingRepository.save(booking);

        NewRatingRequestDTO newRating = NewRatingRequestDTO.builder()
                .bookingId(booking.getId())
                .ratingValue(5.0)
                .feedback("Auto-generated rating for completed ride")
                .tipAmount(0.0)
                .build();
        ratingService.createRating(newRating);

        return bookingMapper.toBookingResponseDTO(updatedBooking);
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
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + bookingId));
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException("Booking is already cancelled.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        validateCancellationPermission(booking, user, cancelledBy);
        booking.setStatus(BookingStatus.CANCELLED);
        booking.setCancelledBy(cancelledBy);
        booking.setCancelledAt(LocalDateTime.now());
        Booking updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toBookingResponseDTO(updatedBooking);
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

    private void validateDriverAvailability(Driver driver, LocalDate date, LocalTime time) {
        boolean isDriverAvailable = bookingRepository.existsByDriverAndDateAndTimeAndStatusNot(
                driver, date, time, BookingStatus.CANCELLED);

        if (isDriverAvailable) {
            throw new IllegalStateException("Driver is not available at the requested time");
        }
    }
}
