package com.drone.controller.request;

import java.util.List;

import javax.validation.constraints.Size;

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
public class MedicationRequest {
	
	@Size(min = 1, message = "At least one medication item must be present")
	private List<MedicationItem> medicationItems;

}
