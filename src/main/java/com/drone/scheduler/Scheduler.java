package com.drone.scheduler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

	@Scheduled(cron = "0 * * * * *")
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

	@Scheduled(cron = "0 */5 * ? * *")
	@Transactional
	public void runAuditScheduler() {
		List<Drone> drones = droneRepository.findAll();
		List<DroneBatteryLevelHistory> history = new ArrayList<>();
		for (Drone drone : drones) {
			DroneBatteryLevelHistory droneBatteryLevelHistory = DroneBatteryLevelHistory.builder()
					.serialNumber(drone.getSerialNumber()).batteryLevel(drone.getBatteryCapacity()).build();
			history.add(droneBatteryLevelHistory);
		}
		droneBatteryLevelHistoryRepository.saveAll(history);

		// removing history records older than two days
		LocalDate twoDaysAgo = LocalDate.now().minus(2, ChronoUnit.DAYS);
		droneBatteryLevelHistoryRepository.deleteByDateBefore(twoDaysAgo);
	}

}
