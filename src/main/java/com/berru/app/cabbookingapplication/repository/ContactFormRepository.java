package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {
    Optional<ContactForm> findByEmail(String email);
}
