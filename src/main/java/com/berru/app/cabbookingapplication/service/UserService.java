package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;

import javax.management.relation.RoleStatus;
import java.util.List;

public interface UserService {

    UserResponseDTO createUser(NewUserRequestDTO newUserRequestDTO);

    UserResponseDTO getUserById(Integer id);

    PaginationResponse<UserResponseDTO> listPaginated(int pageNo, int size);

    List<UserResponseDTO> searchUserByRsql(String query);

    UserResponseDTO updateUser(Integer id, UpdateUserRequestDTO updateUserRequestDTO);

    void deleteUserById(Integer id);

    UserResponseDTO changeUserRole(Integer id, RoleStatus newRole);
}
