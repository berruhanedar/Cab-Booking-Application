package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.service.ContextService;

public class ContextServiceImpl implements ContextService {
    @Override
    public Integer getCurrentUserId() {
        return 0;
    }

    @Override
    public String getCurrentUserEmail() {
        return "";
    }

    @Override
    public boolean hasRole(String roleName) {
        return false;
    }
}
