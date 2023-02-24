package com.drone.service;

import com.drone.controller.request.MedicationRequest;
import com.drone.controller.response.MedicationItemsResponse;

public interface MedicationService {

	MedicationItemsResponse loadMedicationItemsToDrone(String serialNumber, MedicationRequest medicationRequest);

}
