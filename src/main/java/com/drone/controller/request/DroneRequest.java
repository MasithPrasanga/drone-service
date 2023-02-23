package com.drone.controller.request;

import com.drone.util.DroneState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DroneRequest {

	@NonNull
	private String serialNumber;

	@NonNull
	private String model;

	@NonNull
	private Double weight;

	@NonNull
	private Integer batteryCapacity;

	@NonNull
	private DroneState droneState;

}
