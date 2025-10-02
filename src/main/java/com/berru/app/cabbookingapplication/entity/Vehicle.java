package com.berru.app.cabbookingapplication.entity;

import com.berru.app.cabbookingapplication.enums.VehicleTypeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plate", nullable = false, unique = true)
    private String plate;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleTypeStatus type;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

}