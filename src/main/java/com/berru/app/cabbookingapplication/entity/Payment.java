package com.berru.app.cabbookingapplication.entity;

import com.berru.app.cabbookingapplication.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private BookingForm booking;
}
