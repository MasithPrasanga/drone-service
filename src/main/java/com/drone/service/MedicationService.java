package com.drone.service;

import com.drone.controller.request.MedicationRequest;
import com.drone.controller.response.DroneResponse;

public interface MedicationService {

	DroneResponse loadMedicationItemsToDrone(String serialNumber, MedicationRequest medicationRequest);

}
