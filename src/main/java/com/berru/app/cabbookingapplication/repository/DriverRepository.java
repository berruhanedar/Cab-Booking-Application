package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>, JpaSpecificationExecutor<Driver> {
    boolean existsByUserId(Integer userId);
    Optional<Driver> findByUserId(Integer userId);
}
