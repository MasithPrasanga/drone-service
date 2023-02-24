package com.drone.controller.request;

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
	private Double weightLimit;

	@NonNull
	private Integer batteryCapacity;

}
