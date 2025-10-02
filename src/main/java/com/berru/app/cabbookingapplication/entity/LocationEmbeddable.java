package com.berru.app.cabbookingapplication.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEmbeddable {

    private Double latitude;

    private Double longitude;

    private String address;
}