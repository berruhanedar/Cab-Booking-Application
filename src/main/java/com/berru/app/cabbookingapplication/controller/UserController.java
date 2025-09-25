package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import com.berru.app.cabbookingapplication.dto.UpdateUserRequestDTO;
import com.berru.app.cabbookingapplication.dto.UserResponseDTO;
import com.berru.app.cabbookingapplication.service.UserService;
import jakarta.validation.Valid;
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
    public UserResponseDTO createUser(@RequestBody @Valid NewUserRequestDTO newUserRequestDTO) {
        return null;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Integer id) {
        return null;
    }

    @GetMapping
    public PaginationResponse<UserResponseDTO> listPaginated(
            @RequestParam int pageNo,
            @RequestParam int size) {
        return null;
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
}
