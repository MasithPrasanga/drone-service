package com.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drone.controller.request.MedicationRequest;
import com.drone.controller.response.DroneResponse;
import com.drone.service.MedicationService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MedicationController {
	
	@Autowired
	private MedicationService medicationService;
	
	@PostMapping(value="/medication")
	@Operation(summary = "loading a drone with medication items")
	public DroneResponse loadMedicationItemsToDrone(@RequestBody MedicationRequest medicationRequest) {
		DroneResponse droneResponse = medicationService.loadMedicationItemsToDrone(medicationRequest);
		log.info("medications are loaded to drone serial number [ %s ]", medicationRequest.getDroneSerialNumber());
		return droneResponse;
	}

}
