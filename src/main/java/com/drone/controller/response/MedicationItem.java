package com.drone.controller.response;

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
public class MedicationItem {

	private String id;
	private String name;
	private Integer weight;
	private String code;
	private String imageURL;
	private String droneId;

}
