package com.berru.app.cabbookingapplication.entity;

import com.berru.app.cabbookingapplication.enums.DriverAvailability;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @JsonManagedReference
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "driver_license_number", nullable = false, unique = true)
    private String driverLicenseNumber;

    @Column(name = "average_rating")
    private Double averageRating = 0.0;

    @Column(name = "total_rides")
    private Integer totalRides = 0;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "current_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "current_longitude")),
            @AttributeOverride(name = "address", column = @Column(name = "current_address"))
    })
    private Location currentLocation = new Location();

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false)
    private DriverAvailability availability = DriverAvailability.AVAILABLE;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings = new ArrayList<>();

}
