package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookingFormRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {
}
