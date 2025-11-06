package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.NewPaymentRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaymentResponseDTO;
import com.berru.app.cabbookingapplication.entity.Booking;

public interface PaymentService {

    PaymentResponseDTO processPayment(NewPaymentRequestDTO newPaymentRequestDTO);

    PaymentResponseDTO processPayment(Booking booking);

    PaymentResponseDTO refundPayment(Integer bookingId);

    PaymentResponseDTO getPaymentDetails(Integer paymentId);
}