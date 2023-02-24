package com.drone.service;

import java.util.ArrayList;
import java.util.List;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.request.MedicationItem;
import com.drone.controller.response.DroneBatteryLevelResponse;
import com.drone.controller.response.DroneResponse;
import com.drone.controller.response.DronesResponse;
import com.drone.controller.response.MedicationItemResponse;
import com.drone.controller.response.MedicationItemsResponse;
import com.drone.model.Drone;
import com.drone.model.Medication;
import com.drone.util.DroneState;

public class Transformer {

	private Transformer() {
		super();
	}

	public static Drone createDrone(DroneRequest droneRequest) {
		return Drone.builder()
				.serialNumber(droneRequest.getSerialNumber())
				.model(droneRequest.getModel())
				.weightLimit(droneRequest.getWeightLimit())
				.batteryCapacity(droneRequest.getBatteryCapacity())
				.droneState(DroneState.IDLE)
				.build();
	}

	public static DroneResponse createDroneResponse(Drone drone) {
		return DroneResponse.builder()
				.serialNumber(drone.getSerialNumber())
				.model(drone.getModel())
				.weightLimit(drone.getWeightLimit())
				.batteryCapacity(drone.getBatteryCapacity())
				.droneState(drone.getDroneState())
				.build();
	}

	public static Medication createMedication(MedicationItem MedicationItem) {
		return Medication.builder()
				.name(MedicationItem.getName())
				.weight(MedicationItem.getWeight())
				.code(MedicationItem.getCode())
				.imageURL(MedicationItem.getImageURL())
				.build();
	}

	public static MedicationItemsResponse createMedicationResponse(Drone drone ) {		
		List<MedicationItemResponse> medicationItems = new ArrayList<>();
		for (Medication medication : drone.getMedications()) {
			medicationItems.add(createMedicationItem(medication));
		}
		return MedicationItemsResponse.builder()
				.serialNumber(drone.getSerialNumber())
				.model(drone.getModel())
				.weightLimit(drone.getWeightLimit())
				.batteryCapacity(drone.getBatteryCapacity())
				.droneState(drone.getDroneState())
				.medicationItems(medicationItems)
				.build();
	}

	public static MedicationItemResponse createMedicationItem(Medication medication) {
		return MedicationItemResponse.builder()
				.id(medication.getId())
				.name(medication.getName())
				.weight(medication.getWeight())
				.code(medication.getCode())
				.imageURL(medication.getImageURL())
				.droneId(medication.getDrone().getSerialNumber())
				.build();
	}

	public static DronesResponse createDronesResponse(List<Drone> availableDrones) {		
		List<DroneResponse> drons = new ArrayList<>();
		for (Drone drone : availableDrones) {
			drons.add(createDroneResponse(drone));
		}
		
		return DronesResponse.builder()
				.drones(drons)
				.build();
	}

	public static DroneBatteryLevelResponse createBatterLevelResponse(Drone existingDrone) {
		return DroneBatteryLevelResponse.builder()
				.serialNumber(existingDrone.getSerialNumber())
				.batteryCapacity(existingDrone.getBatteryCapacity())
				.build();
	}
}
