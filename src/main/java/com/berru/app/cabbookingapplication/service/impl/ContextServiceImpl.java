package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.config.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.berru.app.cabbookingapplication.service.ContextService;

@Service
public class ContextServiceImpl implements ContextService {

    @Override
    public Integer getCurrentUserId() {
        CustomUserDetails userDetails = getCurrentUserDetails();
        return (userDetails != null) ? userDetails.getUser().getId() : null;
    }

    @Override
    public String getCurrentUserEmail() {
        CustomUserDetails userDetails = getCurrentUserDetails();
        return (userDetails != null) ? userDetails.getUser().getEmail() : null;
    }

    @Override
    public boolean hasRole(String roleName) {
        CustomUserDetails userDetails = getCurrentUserDetails();
        if (userDetails != null) {
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_" + roleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        return null;
    }
}