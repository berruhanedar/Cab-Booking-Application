package com.berru.app.cabbookingapplication.entity;

import com.berru.app.cabbookingapplication.enums.BookingStatus;
import com.berru.app.cabbookingapplication.enums.CancelledBy;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "booking_form")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;

    @Column(name = "from_location")
    private String from;

    @Column(name = "to_location")
    private String to;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "comfort")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status = BookingStatus.PENDING;

    @Column(name = "adult")
    private Integer adult;

    @Column(name = "children")
    private Integer children;

    @Column(name = "message")
    private String message;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Rating rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancelled_by")
    private CancelledBy cancelledBy;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;
}
