package com.berru.app.cabbookingapplication.dto;

import lombok.Data;

@Data
public class ContactFormResponseDTO {

    private Integer id;

    private String name;

    private String email;

    private Long phone;

    private String message;
}
