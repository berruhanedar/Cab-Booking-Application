package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.service.ContextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/context")
public class ContextController {

    private final ContextService contextService;

    public ContextController(ContextService contextService) {
        this.contextService = contextService;
    }

    @GetMapping("/current-user-id")
    public ResponseEntity<?> getCurrentUserId() {
        Integer id = contextService.getCurrentUserId();
        return ResponseEntity.ok(id);
    }

    @GetMapping("/current-user-email")
    public ResponseEntity<?> getCurrentUserEmail() {
        String email = contextService.getCurrentUserEmail();
        return ResponseEntity.ok(email);
    }


    @GetMapping("/has-role/{role}")
    public ResponseEntity<?> hasRole(@PathVariable String role) {
        boolean result = contextService.hasRole(role);
        return ResponseEntity.ok(result);
    }
}
