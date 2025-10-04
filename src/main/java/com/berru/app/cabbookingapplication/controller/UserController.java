package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.UserResponseDTO;
import com.berru.app.cabbookingapplication.enums.RoleStatus;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import com.berru.app.cabbookingapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid NewUserRequestDTO newUserRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(newUserRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<UserResponseDTO>> listPaginated(
            @RequestParam int pageNo,
            @RequestParam int size) {
        PaginationResponse<UserResponseDTO> paginationResponse = userService.listPaginated(pageNo, size);
        return ResponseEntity.ok(paginationResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUserByRsql(@RequestParam String query) {
        List<UserResponseDTO> userResponseDTOList = userService.searchUserByRsql(query);
        return ResponseEntity.ok(userResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateUserRequestDTO updateUserRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(id, updateUserRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<UserResponseDTO> addAddressToUser(
            @PathVariable Integer userId,
            @PathVariable Integer addressId) {
        UserResponseDTO userResponseDTO = userService.addAddressToUser(userId, addressId);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<UserResponseDTO> changeUserRole(
            @PathVariable Integer id,
            @RequestParam RoleStatus newRole) {
        UserResponseDTO userResponseDTO = userService.changeUserRole(id, newRole);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UserResponseDTO> changeUserStatus(
            @PathVariable Integer id,
            @RequestParam UserStatus newStatus) {
        UserResponseDTO userResponseDTO = userService.changeUserStatus(id, newStatus);
        return ResponseEntity.ok(userResponseDTO);
    }
}
