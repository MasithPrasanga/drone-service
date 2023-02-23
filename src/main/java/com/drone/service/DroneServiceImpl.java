package com.drone.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.response.DroneResponse;
import com.drone.exception.ExistingDroneException;
import com.drone.model.Drone;
import com.drone.repository.DroneRepository;
import com.drone.util.Message;

@Service
public class DroneServiceImpl implements DroneService {
	
	@Autowired
	private DroneRepository droneRepository;

	@Override
	public DroneResponse registerDrone(DroneRequest droneRequest) {
		Drone existingDrone = droneRepository.findDroneBySerialNumber(droneRequest.getSerialNumber());
		if (!ObjectUtils.isEmpty(existingDrone)) {
			String description = String.format("drone has already been created for the [ %s ] serial number", droneRequest.getSerialNumber());
			throw new ExistingDroneException(Message.DRONE_FOUND.getDescription(), description, HttpStatus.BAD_REQUEST);
		}
		Drone drone = Transformer.createDrone(droneRequest);
		Drone registeredDrone = droneRepository.save(drone);
		return Transformer.createDroneResponse(registeredDrone);
	}

}









