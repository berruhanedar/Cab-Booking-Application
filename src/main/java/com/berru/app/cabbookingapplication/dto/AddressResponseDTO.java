package com.berru.app.cabbookingapplication.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private Integer id;

    private String addressName;

    private String street;

    private String buildingName;

    private String city;

    private String country;

    private String postalCode;

}
