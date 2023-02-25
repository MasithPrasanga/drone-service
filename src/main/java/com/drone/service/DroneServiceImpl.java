package com.drone.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.response.DroneBatteryLevelResponse;
import com.drone.controller.response.DroneResponse;
import com.drone.controller.response.DronesResponse;
import com.drone.controller.response.MedicationItemsResponse;
import com.drone.exception.DroneNotFoundException;
import com.drone.exception.ExistingDroneException;
import com.drone.model.Drone;
import com.drone.repository.DroneRepository;
import com.drone.util.DroneState;
import com.drone.util.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;

	@Override
	public DroneResponse registerDrone(DroneRequest droneRequest) {
		Drone existingDrone = droneRepository.findDroneBySerialNumber(droneRequest.getSerialNumber());
		if (!ObjectUtils.isEmpty(existingDrone)) {
			String description = String.format("drone has already been created for the [ %s ] serial number",
					droneRequest.getSerialNumber());
			throw new ExistingDroneException(Message.DRONE_FOUND.getDescription(), description, HttpStatus.BAD_REQUEST);
		}
		Drone drone = Transformer.createDrone(droneRequest);
		Drone registeredDrone = droneRepository.save(drone);
		return Transformer.createDroneResponse(registeredDrone);
	}

	@Override
	public MedicationItemsResponse getMedicationItems(String serialNumber) {
		Drone existingDrone = droneRepository.findDroneBySerialNumber(serialNumber);
		if (ObjectUtils.isEmpty(existingDrone)) {
			String description = String.format("drone not found for the [ %s ] serial number", serialNumber);
			throw new DroneNotFoundException(Message.DRONE_NOT_FOUND.getDescription(), description,
					HttpStatus.BAD_REQUEST);
		}
		return Transformer.createMedicationResponse(existingDrone);
	}

	@Override
	public DronesResponse getAvailableDrones() {
		List<Drone> availableDrones = droneRepository.findDroneByDroneState(DroneState.IDLE);
		return Transformer.createDronesResponse(availableDrones);
	}

	@Override
	public DroneBatteryLevelResponse getBatteryLevel(String serialNumber) {
		Drone existingDrone = droneRepository.findDroneBySerialNumber(serialNumber);
		if (ObjectUtils.isEmpty(existingDrone)) {
			String description = String.format("drone not found for the [ %s ] serial number", serialNumber);
			throw new DroneNotFoundException(Message.DRONE_NOT_FOUND.getDescription(), description,
					HttpStatus.BAD_REQUEST);
		}
		return Transformer.createBatterLevelResponse(existingDrone);
	}

}
