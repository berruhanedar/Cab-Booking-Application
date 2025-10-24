package com.berru.app.cabbookingapplication.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private Double latitude = 0.0;

    private Double longitude = 0.0;

    private String address = "";
}