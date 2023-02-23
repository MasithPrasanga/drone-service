package com.drone.controller.response;

import com.drone.util.DroneState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DroneResponse {

	private String id;
	private String serialNumber;
	private String model;
	private Double weight;
	private Integer batteryCapacity;
	private DroneState droneState;

}
