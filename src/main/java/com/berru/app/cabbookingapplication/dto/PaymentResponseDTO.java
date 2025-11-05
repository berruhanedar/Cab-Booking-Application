package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.PaymentStatus;
import com.berru.app.cabbookingapplication.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentResponseDTO {

    private Integer id;

    private Double amount;

    private PaymentType type;

    private PaymentStatus status;

    private String transactionId;

    private Integer driverId;

    private String driverName;

    private Integer bookingId;
}
