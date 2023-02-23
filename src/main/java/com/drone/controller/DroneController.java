package com.drone.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.response.DroneResponse;
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
	public DroneResponse createDrone(@Valid @RequestBody DroneRequest droneRequest) {
		DroneResponse registeredDrone = droneService.registerDrone(droneRequest);
		log.info("drone is created with id [ {} ]", registeredDrone.getId());
		return registeredDrone;
	}

}
