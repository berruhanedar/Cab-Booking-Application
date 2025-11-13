package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>, JpaSpecificationExecutor<Payment> {
    Optional<Payment> findByBookingId(Integer bookingId);
}
