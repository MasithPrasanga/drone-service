package com.drone.service;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.response.DroneBatteryLevelResponse;
import com.drone.controller.response.DroneResponse;
import com.drone.controller.response.DronesResponse;
import com.drone.controller.response.MedicationItemsResponse;

public interface DroneService {

	DroneResponse registerDrone(DroneRequest droneRequest);

	MedicationItemsResponse getMedicationItems(String serialNumber);

	DronesResponse getAvailableDrones();

	DroneBatteryLevelResponse getBatteryLevel(String serialNumber);

}
