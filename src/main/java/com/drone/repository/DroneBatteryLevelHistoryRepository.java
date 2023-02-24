package com.drone.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drone.model.DroneBatteryLevelHistory;

@Repository
public interface DroneBatteryLevelHistoryRepository extends JpaRepository<DroneBatteryLevelHistory, String> {

	void deleteByDateBefore(LocalDate date);

}
