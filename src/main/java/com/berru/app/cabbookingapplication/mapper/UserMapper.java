package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.UpdateUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.UserResponseDTO;
import com.berru.app.cabbookingapplication.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(NewUserRequestDTO newUserRequestDTO);

    UserResponseDTO toUserResponseDTO(User user);

    void updateUserFromDTO(UpdateUserRequestDTO updateUserRequestDTO, @MappingTarget User user);

}
