package com.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.request.MedicationRequest;
import com.drone.controller.response.DroneBatteryLevelResponse;
import com.drone.controller.response.DroneResponse;
import com.drone.controller.response.DronesResponse;
import com.drone.controller.response.MedicationItemsResponse;
import com.drone.service.DroneService;
import com.drone.service.MedicationService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/drone")
@Slf4j
public class DispatchController {

	@Autowired
	private DroneService droneService;

	@Autowired
	private MedicationService medicationService;

	@PostMapping
	@Operation(summary = "registering a drone")
	@ResponseStatus(HttpStatus.CREATED)
	public DroneResponse createDrone(@RequestBody DroneRequest droneRequest) {
		DroneResponse registeredDrone = droneService.registerDrone(droneRequest);
		log.info("drone is created with serial number [ {} ]", registeredDrone.getSerialNumber());
		return registeredDrone;
	}

	@PostMapping(value = "/{droneId}/medication")
	@Operation(summary = "loading a drone with medication items")
	public MedicationItemsResponse loadMedicationItemsToDrone(@PathVariable("droneId") String serialNumber,
			@RequestBody MedicationRequest medicationRequest) {
		MedicationItemsResponse medicationItemsResponse = medicationService.loadMedicationItemsToDrone(serialNumber,
				medicationRequest);
		log.info("medications are loaded to drone serial number [ %s ]", serialNumber);
		return medicationItemsResponse;
	}

	@GetMapping(value = "/{droneId}/medication")
	@Operation(summary = "check loaded medication items for a given drone")
	public MedicationItemsResponse getMedicationItemsForDrone(@PathVariable("droneId") String serialNumber) {
		return droneService.getMedicationItems(serialNumber);
	}

	@GetMapping("/available")
	@Operation(summary = "check available drones for loading")
	public DronesResponse getAvailableDrones() {
		return droneService.getAvailableDrones();
	}

	@GetMapping("/{droneId}/battery")
	@Operation(summary = "check battery level for a drone")
	public DroneBatteryLevelResponse getBatteryLevel(@PathVariable("droneId") String serialNumber) {
		return droneService.getBatteryLevel(serialNumber);
	}

}
