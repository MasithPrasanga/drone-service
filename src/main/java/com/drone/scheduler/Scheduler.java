package com.drone.scheduler;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.drone.model.Drone;
import com.drone.model.DroneBatteryLevelHistory;
import com.drone.repository.DroneBatteryLevelHistoryRepository;
import com.drone.repository.DroneRepository;
import com.drone.util.DroneState;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Scheduler {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private DroneBatteryLevelHistoryRepository droneBatteryLevelHistoryRepository;

	/**
	 * This scheduler is responsible to update the battery level
	 * if the drone is at IDEL state for every minute battery level is increasing 2% 
	 * if the drone is at any other state for every minute battery level is decreasing 1%
	 * 
	 * This runs every minute
	 */
	@Scheduled(cron = "0 * * * * *") // run every minute
	@Transactional
	public void runDroneBatteryScheduler() {
		List<Drone> drones = droneRepository.findAll();
		for (Drone drone : drones) {
			if (drone.getDroneState().equals(DroneState.IDLE)) {
				drone.setBatteryCapacity(Math.min(drone.getBatteryCapacity() + 2, 100));
			} else {
				drone.setBatteryCapacity(Math.max(drone.getBatteryCapacity() - 1, 0));
			}

		}
		droneRepository.saveAll(drones);
	}

	/**
	 * This scheduler is responsible to add batter level to the drone battery level history table
	 * 
	 * This runs every 5 minutes
	 */
	@Scheduled(cron = "0 */5 * ? * *")
	@Transactional
	public void runAuditScheduler() {
		List<Drone> drones = droneRepository.findAll();
		List<DroneBatteryLevelHistory> history = new ArrayList<>();
		for (Drone drone : drones) {
			DroneBatteryLevelHistory droneBatteryLevelHistory = DroneBatteryLevelHistory.builder()
					.serialNumber(drone.getSerialNumber())
					.batteryLevel(drone.getBatteryCapacity())
					.createdDate(new Date())
					.build();
			history.add(droneBatteryLevelHistory);
		}
		droneBatteryLevelHistoryRepository.saveAll(history);		
	}
	
	/**
	 * This scheduler is responsible to delete all the history records
	 * older than two days
	 * 
	 * This run once per day at midnight
	 */
	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void deleteOldRecords() {
		LocalDate twoDaysAgo = LocalDate.now(ZoneId.systemDefault()).minusDays(2);
		Date date = Date.from(twoDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
		droneBatteryLevelHistoryRepository.deleteByCreatedDateBefore(date);
	}

}
