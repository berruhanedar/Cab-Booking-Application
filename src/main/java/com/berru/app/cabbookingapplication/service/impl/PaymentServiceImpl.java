package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.NewPaymentRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaymentResponseDTO;
import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.entity.Driver;
import com.berru.app.cabbookingapplication.entity.Payment;
import com.berru.app.cabbookingapplication.enums.PaymentMethod;
import com.berru.app.cabbookingapplication.enums.PaymentType;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.PaymentMapper;
import com.berru.app.cabbookingapplication.repository.BookingRepository;
import com.berru.app.cabbookingapplication.repository.DriverRepository;
import com.berru.app.cabbookingapplication.repository.PaymentRepository;
import com.berru.app.cabbookingapplication.service.FareCalculationService;
import com.berru.app.cabbookingapplication.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;
    private final DriverRepository driverRepository;
    private final FareCalculationService fareCalculationService;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              BookingRepository bookingRepository,
                              PaymentMapper paymentMapper,
                              DriverRepository driverRepository,
                              FareCalculationService fareCalculationService) {

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.paymentMapper = paymentMapper;
        this.driverRepository = driverRepository;
        this.fareCalculationService = fareCalculationService;
    }

    @Override
    @Transactional
    public PaymentResponseDTO processPayment(NewPaymentRequestDTO paymentRequestDTO) {

        Booking booking = bookingRepository.findById(paymentRequestDTO.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + paymentRequestDTO.getBookingId()));

        Driver driver = driverRepository.findById(paymentRequestDTO.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + paymentRequestDTO.getDriverId()));

        Payment payment = paymentMapper.toPayment(paymentRequestDTO);
        payment.setBooking(booking);
        payment.setDriver(driver);

        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toPaymentResponseDTO(saved);
    }

    @Transactional
    public PaymentResponseDTO processPayment(Booking booking) {

        double amount = fareCalculationService.calculateFare(booking);

        NewPaymentRequestDTO dto = NewPaymentRequestDTO.builder()
                .amount(amount)
                .bookingId(booking.getId())
                .driverId(booking.getDriver().getId())
                .method(PaymentMethod.DEFAULT)
                .type(PaymentType.DEFAULT)
                .transactionId(generateTransactionId())
                .build();

        return processPayment(dto);
    }

    @Override
    public PaymentResponseDTO refundPayment(Integer bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));

        Payment existingPayment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("No payment found for booking id " + bookingId));

        if (existingPayment.getType() == PaymentType.REFUND) {
            throw new IllegalStateException("Payment already refunded for booking id " + bookingId);
        }

        NewPaymentRequestDTO refundDTO = NewPaymentRequestDTO.builder()
                .bookingId(bookingId)
                .driverId(existingPayment.getDriver().getId())
                .amount(-existingPayment.getAmount())
                .method(existingPayment.getMethod())
                .type(PaymentType.REFUND)
                .transactionId("REF-" + existingPayment.getTransactionId())
                .build();

        Payment refund = paymentMapper.toPayment(refundDTO);
        refund.setBooking(booking);
        refund.setDriver(existingPayment.getDriver());

        Payment saved = paymentRepository.save(refund);
        return paymentMapper.toPaymentResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDTO getPaymentDetails(Integer paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("No payment found with id " + paymentId));

        return paymentMapper.toPaymentResponseDTO(payment);
    }

    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis();
    }
}
