package com.berru.app.cabbookingapplication.entity;

import com.berru.app.cabbookingapplication.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "booking_form")
public class BookingForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

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

    @Column(name = "adult")
    private Integer adult;

    @Column(name = "children")
    private Integer children;

    @Column(name = "message")
    private String message;
}
