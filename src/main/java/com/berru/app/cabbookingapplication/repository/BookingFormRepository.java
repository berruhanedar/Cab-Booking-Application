package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.BookingForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingFormRepository extends JpaRepository<BookingForm, Integer> {
}
