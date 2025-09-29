package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.UserResponseDTO;
import com.berru.app.cabbookingapplication.entity.Address;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import com.berru.app.cabbookingapplication.exception.DuplicateEmailException;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.UserMapper;
import com.berru.app.cabbookingapplication.repository.AddressRepository;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.service.UserService;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleStatus;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public UserResponseDTO createUser(NewUserRequestDTO newUserRequestDTO) {
        User user = userMapper.toUser(newUserRequestDTO);

        Address address = addressRepository.findById(newUserRequestDTO.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + newUserRequestDTO.getAddressId()));

        validateNewUser(newUserRequestDTO);

        address.setUser(user);
        user.getAddresses().add(address);

        User savedUser = userRepository.save(user);

        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public PaginationResponse<UserResponseDTO> listPaginated(int pageNo, int size) {
        return null;
    }

    @Override
    public List<UserResponseDTO> searchUserByRsql(String query) {
        return List.of();
    }

    @Override
    public UserResponseDTO updateUser(Integer id, UpdateUserRequestDTO updateUserRequestDTO) {
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {
    }

    @Override
    public UserResponseDTO changeUserRole(Integer id, RoleStatus newRole) {
        return null;
    }

    @Override
    public UserResponseDTO changeUserStatus(Integer id, UserStatus newStatus) {
        return null;
    }

    private void validateNewUser(NewUserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }
    }
}
