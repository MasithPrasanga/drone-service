package com.drone.service;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.response.DroneResponse;

public interface DroneService {

	DroneResponse registerDrone(DroneRequest droneRequest);

}
