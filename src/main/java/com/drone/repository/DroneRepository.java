package com.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drone.model.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

	Drone findDroneBySerialNumber(String serialNumber);

}
