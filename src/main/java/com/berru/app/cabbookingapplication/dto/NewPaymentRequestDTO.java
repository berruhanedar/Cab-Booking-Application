package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.PaymentMethod;
import com.berru.app.cabbookingapplication.enums.PaymentType;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NewPaymentRequestDTO {

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "Payment type cannot be null")
    private PaymentType type;

    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod method;

    @NotBlank(message = "Transaction ID cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9\\-]{6,50}$", message = "Transaction ID must be alphanumeric and between 6-50 characters")
    private String transactionId;

    @NotNull(message = "Driver ID cannot be null")
    private Integer driverId;

    @NotNull(message = "Booking ID cannot be null")
    private Integer bookingId;
}
