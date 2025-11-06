package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.BookingResponseDTO;
import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.enums.BookingStatus;
import com.berru.app.cabbookingapplication.enums.PaymentStatus;
import com.berru.app.cabbookingapplication.exception.InvalidBookingStateException;
import com.berru.app.cabbookingapplication.exception.PaymentFailedException;
import com.berru.app.cabbookingapplication.mapper.BookingMapper;
import com.berru.app.cabbookingapplication.repository.BookingRepository;
import com.berru.app.cabbookingapplication.service.PaymentService;
import com.berru.app.cabbookingapplication.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingWorkflowService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

    public BookingWorkflowService(BookingRepository bookingRepository,
                                  BookingMapper bookingMapper,
                                  PaymentService paymentService,
                                  RatingService ratingService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.paymentService = paymentService;
        this.ratingService = ratingService;
    }

    public BookingResponseDTO completeBookingFlow(Booking booking) {
        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new InvalidBookingStateException("Only confirmed bookings can be completed.");
        }

        var paymentResponse = paymentService.processPayment(booking);
        if (paymentResponse.getStatus() != PaymentStatus.SUCCESS) {
            throw new PaymentFailedException("Payment failed for booking " + booking.getId());
        }

        ratingService.createRating(booking);
        booking.setStatus(BookingStatus.COMPLETED);
        bookingRepository.save(booking);

        return bookingMapper.toBookingResponseDTO(booking);
    }
}
