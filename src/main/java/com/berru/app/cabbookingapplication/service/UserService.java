package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.dto.*;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleStatus;
import java.util.List;

@Service
public interface UserService {

    UserResponseDTO createUser(NewUserRequestDTO newUserRequestDTO);

    UserResponseDTO getUserById(Integer id);

    PaginationResponse<UserResponseDTO> listPaginated(int pageNo, int size);

    List<UserResponseDTO> searchUserByRsql(String query);

    UserResponseDTO updateUser(Integer id, UpdateUserRequestDTO updateUserRequestDTO);

    void deleteUserById(Integer id);

    UserResponseDTO changeUserRole(Integer id, RoleStatus newRole);
}
