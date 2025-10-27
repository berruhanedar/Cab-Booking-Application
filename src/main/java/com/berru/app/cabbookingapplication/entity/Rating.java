package com.berru.app.cabbookingapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Rating value cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    @Column(name = "rating_value", nullable = false)
    private Double ratingValue;

    @Column(name = "feedback", length = 500)
    private String feedback;

    @Column(name = "tip_amount")
    private Double tipAmount = 0.0;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    @JsonBackReference
    private Booking booking;

}
