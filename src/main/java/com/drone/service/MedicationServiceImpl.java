package com.drone.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.drone.controller.request.MedicationItem;
import com.drone.controller.request.MedicationRequest;
import com.drone.controller.response.MedicationItemsResponse;
import com.drone.exception.DroneBatteryLowException;
import com.drone.exception.DroneNotFoundException;
import com.drone.exception.DroneNotIdleException;
import com.drone.exception.WeightLimitExceedException;
import com.drone.model.Drone;
import com.drone.model.Medication;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;
import com.drone.util.DroneState;
import com.drone.util.Message;

@Service
public class MedicationServiceImpl implements MedicationService {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private MedicationRepository medicationRepository;

	@Override
	public MedicationItemsResponse loadMedicationItemsToDrone(String serialNumber,
			MedicationRequest medicationRequest) {
		Drone existingDrone = droneRepository.findDroneBySerialNumber(serialNumber);
		if (ObjectUtils.isEmpty(existingDrone)) {
			String description = String.format("drone not found for the [ %s ] serial number", serialNumber);
			throw new DroneNotFoundException(Message.DRONE_NOT_FOUND.getDescription(), description,
					HttpStatus.NOT_FOUND);
		}

		if (!existingDrone.getDroneState().equals(DroneState.IDLE)) {
			String description = "To load medications drone must be in the IDEL state";
			throw new DroneNotIdleException(Message.DRONE_STATE_IS_NOT_IDLE.getDescription(), description,
					HttpStatus.BAD_REQUEST);
		}

		if (existingDrone.getBatteryCapacity() < 25) {
			String description = "drone battery capacity must be at least 25%";
			throw new DroneBatteryLowException(Message.DRONE_BATTERY_LOW.getDescription(), description,
					HttpStatus.BAD_REQUEST);
		}

		double totalWeight = 0;
		for (MedicationItem MedicationItem : medicationRequest.getMedicationItems()) {
			totalWeight = totalWeight + MedicationItem.getWeight();
		}

		if (totalWeight <= existingDrone.getWeightLimit()) {
			existingDrone.setDroneState(DroneState.LOADING);
			droneRepository.save(existingDrone);
		} else {
			String description = String.format("maximum medication weight this drone can carry is [ %s ] and medication weight is [ %s ]", existingDrone.getWeightLimit(), totalWeight);
			throw new WeightLimitExceedException(Message.EXCEED_WEIGHT_LIMIT.getDescription(), description,
					HttpStatus.BAD_REQUEST);
		}

		List<Medication> medications = new ArrayList<>();
		for (MedicationItem MedicationItem : medicationRequest.getMedicationItems()) {
			Medication medication = Transformer.createMedication(MedicationItem);
			medication.setDrone(existingDrone);
			medications.add(medication);
		}
		medicationRepository.saveAll(medications);

		existingDrone.setMedications(medications);
		existingDrone.setDroneState(DroneState.LOADED);
		Drone updatedDrone = droneRepository.save(existingDrone);
		return Transformer.createMedicationResponse(updatedDrone);
	}
}
