package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.BookingStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponseDTO {

    private Integer id;

    private Integer userId;

    private Integer driverId;

    private String from;

    private String to;

    private LocalDate date;

    private LocalTime time;

    private VehicleType vehicleType;

    private Integer adult;

    private Integer children;

    private String message;

    private BookingStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime cancelledDate;

    private String cancelledBy;
}
