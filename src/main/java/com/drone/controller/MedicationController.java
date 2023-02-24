package com.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.controller.request.MedicationRequest;
import com.drone.controller.response.MedicationItemsResponse;
import com.drone.service.DroneService;
import com.drone.service.MedicationService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/drone")
@Slf4j
public class MedicationController {

	@Autowired
	private MedicationService medicationService;

	@Autowired
	private DroneService droneService;

	@PostMapping(value = "/{droneId}/medication")
	@Operation(summary = "loading a drone with medication items")
	public MedicationItemsResponse loadMedicationItemsToDrone(@PathVariable("droneId") String serialNumber,
			@RequestBody MedicationRequest medicationRequest) {
		MedicationItemsResponse medicationItemsResponse = medicationService.loadMedicationItemsToDrone(serialNumber, medicationRequest);
		log.info("medications are loaded to drone serial number [ %s ]", serialNumber);
		return medicationItemsResponse;
	}

	@GetMapping(value = "/{id}/medication")
	@Operation(summary = "get loaded medication items for a given drone")
	public MedicationItemsResponse getMedicationItemsForDrone(@PathVariable("id") String droneId) {
		return droneService.getMedicationItems(droneId);
	}

}
