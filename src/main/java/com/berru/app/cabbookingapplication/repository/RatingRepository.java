package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RatingRepository extends JpaRepository<Rating, Integer> , JpaSpecificationExecutor<Rating> {
}