package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewPaymentRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaymentResponseDTO;
import com.berru.app.cabbookingapplication.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody @Valid NewPaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO responseDTO = paymentService.processPayment(paymentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentDetails(@PathVariable Integer paymentId) {
        PaymentResponseDTO responseDTO = paymentService.getPaymentDetails(paymentId);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/refund/{bookingId}")
    public ResponseEntity<PaymentResponseDTO> refundPayment(@PathVariable Integer bookingId) {
        PaymentResponseDTO responseDTO = paymentService.refundPayment(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
