package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Integer>, JpaSpecificationExecutor<ContactForm> {

    Optional<ContactForm> findByEmail(String email);

}
