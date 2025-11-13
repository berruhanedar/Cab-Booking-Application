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
import com.berru.app.cabbookingapplication.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;
    private final DriverRepository driverRepository;
    private final DistanceService distanceService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository, PaymentMapper paymentMapper, DriverRepository driverRepository, DistanceService distanceService) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.paymentMapper = paymentMapper;
        this.driverRepository = driverRepository;
        this.distanceService = distanceService;
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

        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toPaymentResponseDTO(savedPayment);
    }

    /**
     * Booking objesini alır, DTO oluşturur ve mevcut processPayment metodunu çağırır.
     * Böylece BookingService'de tekrar DTO oluşturmak zorunda kalmazsın.
     */
    @Transactional
    public PaymentResponseDTO processPayment(Booking booking) {
        NewPaymentRequestDTO paymentRequestDTO = NewPaymentRequestDTO.builder()
                .amount(calculateBookingAmount(booking))
                .bookingId(booking.getId())
                .driverId(booking.getDriver().getId())
                .method(PaymentMethod.DEFAULT)
                .type(PaymentType.DEFAULT)
                .transactionId(generateTransactionId())
                .build();
        return processPayment(paymentRequestDTO);
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
        Payment refundPayment = paymentMapper.toPayment(refundDTO);
        refundPayment.setBooking(booking);
        refundPayment.setDriver(existingPayment.getDriver());
        Payment savedRefund = paymentRepository.save(refundPayment);
        return paymentMapper.toPaymentResponseDTO(savedRefund);
    }

    @Override
    public PaymentResponseDTO getPaymentDetails(Integer paymentId) {
        return null;
    }

    private Double calculateBookingAmount(Booking booking) {
        double baseFare = 50.0;

        double distanceKm = distanceService.getDistanceInKm(booking.getFrom(), booking.getTo());
        double distanceFare = distanceKm * 2.0;

        double vehicleMultiplier;
        switch (booking.getVehicleType()) {
            case LUXURY -> vehicleMultiplier = 2.0;
            case XL -> vehicleMultiplier = 1.8;
            case COMFORT -> vehicleMultiplier = 1.2;
            default -> vehicleMultiplier = 1.0;
        }

        double passengerExtra = (booking.getAdult() + booking.getChildren() * 0.5);

        return (baseFare + distanceFare) * vehicleMultiplier + passengerExtra;
    }

    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis();
    }

}
