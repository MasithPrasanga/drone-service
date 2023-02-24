package com.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.response.DroneBatteryLevelResponse;
import com.drone.controller.response.DroneResponse;
import com.drone.controller.response.DronesResponse;
import com.drone.service.DroneService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/drone")
@Slf4j
public class DroneController {

	@Autowired
	private DroneService droneService;

	@PostMapping
	@Operation(summary = "register drone")
	public DroneResponse createDrone(@RequestBody DroneRequest droneRequest) {
		DroneResponse registeredDrone = droneService.registerDrone(droneRequest);
		log.info("drone is created with serial number [ {} ]", registeredDrone.getSerialNumber());
		return registeredDrone;
	}

	@GetMapping("/available")
	@Operation(summary = "get available drones for loading")
	public DronesResponse getAvailableDrones() {
		return droneService.getAvailableDrones();
	}

	@GetMapping("/{id}/battery")
	@Operation(summary = "get battery level for a drone")
	public DroneBatteryLevelResponse getBatteryLevel(@PathVariable("id") String serialNumber) {
		return droneService.getBatteryLevel(serialNumber);
	}
}
