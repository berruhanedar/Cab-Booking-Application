package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import com.berru.app.cabbookingapplication.enums.RoleStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponseDTO createUser(NewUserRequestDTO newUserRequestDTO);

    UserResponseDTO getUserById(Integer id);

    PaginationResponse<UserResponseDTO> listPaginated(int pageNo, int size);

    List<UserResponseDTO> searchUserByRsql(String query);

    UserResponseDTO updateUser(Integer id, UpdateUserRequestDTO updateUserRequestDTO);

    UserResponseDTO addAddressToUser(Integer userId, Integer addressId);

    void deleteUserById(Integer id);

    UserResponseDTO changeUserRole(Integer targetUserId, RoleStatus newRole);

    UserResponseDTO changeUserStatus(Integer id, UserStatus newStatus);
}
