package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.PaymentStatus;
import com.berru.app.cabbookingapplication.enums.PaymentType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePaymentRequestDTO {

    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    private PaymentType type;

    private PaymentStatus status;

    @Pattern(regexp = "^[A-Za-z0-9\\-]{6,50}$", message = "Transaction ID must be alphanumeric and between 6-50 characters")
    private String transactionId;

    private Integer driverId;

    private Integer bookingId;
}
