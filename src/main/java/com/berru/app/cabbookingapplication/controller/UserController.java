package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.UserResponseDTO;
import com.berru.app.cabbookingapplication.enums.UserStatus;
import com.berru.app.cabbookingapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleStatus;
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
    public List<UserResponseDTO> searchUserByRsql(@RequestParam String query) {
        return List.of();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable Integer id,
            @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
    }

    @PatchMapping("/{id}/role")
    public UserResponseDTO changeUserRole(
            @PathVariable Integer id,
            @RequestParam RoleStatus newRole) {
        return null;
    }

    @PatchMapping("/{id}/status")
    public UserResponseDTO changeUserStatus(
            @PathVariable Integer id,
            @RequestParam UserStatus newStatus) {
        return null;
    }


}
