package com.berru.app.cabbookingapplication.entity;

import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
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
    @Column(name = "type", nullable = false)
    private VehicleType type = VehicleType.STANDARD;

    @Enumerated(EnumType.STRING)
    @Column(name = "energy_type", nullable = false)
    private VehicleEnergyType energyType = VehicleEnergyType.FUEL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status = VehicleStatus.AVAILABLE;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

}