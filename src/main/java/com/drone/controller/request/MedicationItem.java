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
public class MedicationItem {

	@NonNull
	private String name;

	@NonNull
	private Double weight;

	@NonNull
	private String code;

	private String imageURL;

}
