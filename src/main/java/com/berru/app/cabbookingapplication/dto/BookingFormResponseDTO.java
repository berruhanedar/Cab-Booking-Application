package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.ComfortStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingFormResponseDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String from;

    private String to;

    private LocalTime time;

    private LocalDate date;

    private ComfortStatus comfort;

    private Integer adult;

    private Integer children;

    private String message;

}
