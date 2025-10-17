package com.berru.app.cabbookingapplication.repository;

import com.berru.app.cabbookingapplication.entity.Vehicle;
import com.berru.app.cabbookingapplication.enums.VehicleEnergyType;
import com.berru.app.cabbookingapplication.enums.VehicleStatus;
import com.berru.app.cabbookingapplication.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>, JpaSpecificationExecutor<Vehicle> {

    Optional<Vehicle> findByPlate(String plate);

    List<Vehicle> findByType(VehicleType type);

    List<Vehicle> findByEnergyType(VehicleEnergyType energyType);

    List<Vehicle> findByStatus (VehicleStatus  status);

    List<Vehicle> findByDriverId(Integer driverId);


}