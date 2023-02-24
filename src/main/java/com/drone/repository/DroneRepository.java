package com.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drone.model.Drone;
import com.drone.util.DroneState;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

	Drone findDroneBySerialNumber(String serialNumber);

	List<Drone> findDroneByDroneState(DroneState droneState);

}
