package com.berru.app.cabbookingapplication.dto;

import lombok.Data;

@Data
public class RatingResponseDTO {

    private Integer id;

    private Double ratingValue;

    private String feedback;

    private Double tipAmount;

    private Integer bookingId;

    private Integer driverId;

    private String driverName;

    private String userName;
}
