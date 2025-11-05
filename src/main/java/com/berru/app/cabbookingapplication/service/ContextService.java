package com.berru.app.cabbookingapplication.service;

/* Amaç: Sistemde o an oturum açmış kullanıcıyı veya rolünü almak.
Spring Security kullanıyorsan SecurityContextHolder’dan wrapper olabilir.
 */
public interface ContextService {

    Integer getCurrentUserId();

    String getCurrentUserEmail();

    boolean hasRole(String roleName);
}
