package com.berru.app.cabbookingapplication.dto;

import com.berru.app.cabbookingapplication.enums.RoleStatus;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private RoleStatus role;

    private UserStatus status;

    private List<AddressResponseDTO> addresses;

}
