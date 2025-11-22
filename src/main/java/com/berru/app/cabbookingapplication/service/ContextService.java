package com.berru.app.cabbookingapplication.service;

public interface ContextService {

    Integer getCurrentUserId();

    String getCurrentUserEmail();

    boolean hasRole(String roleName);
}
