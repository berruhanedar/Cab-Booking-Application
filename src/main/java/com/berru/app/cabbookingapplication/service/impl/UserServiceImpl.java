package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.dto.*;
import com.berru.app.cabbookingapplication.entity.Address;
import com.berru.app.cabbookingapplication.entity.User;
import com.berru.app.cabbookingapplication.enums.RoleStatus;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import com.berru.app.cabbookingapplication.exception.DuplicateEmailException;
import com.berru.app.cabbookingapplication.exception.ResourceNotFoundException;
import com.berru.app.cabbookingapplication.mapper.PaginationMapper;
import com.berru.app.cabbookingapplication.mapper.UserMapper;
import com.berru.app.cabbookingapplication.repository.AddressRepository;
import com.berru.app.cabbookingapplication.repository.UserRepository;
import com.berru.app.cabbookingapplication.service.UserService;
import com.berru.app.cabbookingapplication.service.base.GenericRsqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends GenericRsqlService<User, UserResponseDTO> implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;
    private final PaginationMapper paginationMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AddressRepository addressRepository, PaginationMapper paginationMapper) {
        super(userRepository, userMapper::toUserResponseDTO);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressRepository = addressRepository;
        this.paginationMapper = paginationMapper;
    }

    @Override
    @Transactional
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
    @Transactional
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    @Transactional
    public PaginationResponse<UserResponseDTO> listPaginated(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);

        Page<User> userPage = userRepository.findAll(pageable);

        return paginationMapper.toPaginationResponse(userPage, userMapper::toUserResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> searchUserByRsql(String query) {
        return searchByRsql(query);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Integer id, UpdateUserRequestDTO updateUserRequestDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userMapper.updateUserFromDTO(updateUserRequestDTO, existingUser);
        User savedUser = userRepository.save(existingUser);
        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDTO addAddressToUser(Integer userId, Integer addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));

        if (address.getUser() != null && !address.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Address already belongs to another user");
        }

        boolean alreadyHasAddress = user.getAddresses().stream()
                .anyMatch(addr -> addr.getId().equals(addressId));

        if (alreadyHasAddress) {
            throw new IllegalArgumentException("User already has this address");
        }

        address.setUser(user);
        user.getAddresses().add(address);

        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponseDTO(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserResponseDTO changeUserRole(Integer id, RoleStatus newRole) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user.setRole(newRole);
        userRepository.save(user);
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO changeUserStatus(Integer id, UserStatus newStatus) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user.setStatus(newStatus);
        userRepository.save(user);
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public User getUserByEmailAndStatus(String email, UserStatus status) {
        return userRepository.findByEmailAndStatus(email, status)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void validateNewUser(NewUserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }
    }
}
