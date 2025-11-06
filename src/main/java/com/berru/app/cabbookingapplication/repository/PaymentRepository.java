package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>, JpaSpecificationExecutor<Payment> {
}
