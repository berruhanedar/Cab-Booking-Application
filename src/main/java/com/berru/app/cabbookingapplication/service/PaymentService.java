package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.NewPaymentRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaymentResponseDTO;

public interface PaymentService {

    PaymentResponseDTO processPayment(NewPaymentRequestDTO newPaymentRequestDTO);

    PaymentResponseDTO refundPayment(Integer bookingId);

    PaymentResponseDTO getPaymentDetails(Integer paymentId);
}