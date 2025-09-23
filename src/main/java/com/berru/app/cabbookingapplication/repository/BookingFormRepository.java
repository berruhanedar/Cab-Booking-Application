package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.BookingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookingFormRepository extends JpaRepository<BookingForm, Integer>, JpaSpecificationExecutor<BookingForm> {
}
