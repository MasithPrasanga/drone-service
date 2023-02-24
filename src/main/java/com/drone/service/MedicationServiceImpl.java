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
import com.drone.exception.DroneNotFoundException;
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
					HttpStatus.BAD_REQUEST);
		}

		// drone should be IDLE state

		// medication weight should not exceed

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
