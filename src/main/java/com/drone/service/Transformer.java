package com.drone.service;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.request.MedicationItem;
import com.drone.controller.response.DroneResponse;
import com.drone.model.Drone;
import com.drone.model.Medication;

public class Transformer {
	
	private Transformer() {
		super();
	}
	
	public static Drone createDrone(DroneRequest droneRequest) {
		return Drone.builder()
				.serialNumber(droneRequest.getSerialNumber())
				.model(droneRequest.getModel())
				.weight(droneRequest.getWeight())
				.batteryCapacity(droneRequest.getBatteryCapacity())
				.droneState(droneRequest.getDroneState())
				.build();
	}
	
	public static DroneResponse createDroneResponse(Drone drone) {
		return DroneResponse.builder()
				.id(drone.getSerialNumber())
				.serialNumber(drone.getSerialNumber())
				.model(drone.getModel())
				.weight(drone.getWeight())
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
}
