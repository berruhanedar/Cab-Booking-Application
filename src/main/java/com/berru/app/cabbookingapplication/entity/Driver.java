package com.berru.app.cabbookingapplication.entity;

import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "driver_license_number", nullable = false, unique = true)
    private String driverLicenseNumber;

    @Column(name = "rating")
    private Double rating = 5.0;

    @Column(name = "total_rides")
    private Integer totalRides = 0;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "current_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "current_longitude")),
            @AttributeOverride(name = "address", column = @Column(name = "current_address"))
    })
    private LocationEmbeddable currentLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false)
    private DriverAvailability availability = DriverAvailability.OFFLINE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
