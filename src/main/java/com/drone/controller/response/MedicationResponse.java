package com.drone.controller.response;

import java.util.List;

import com.drone.controller.request.MedicationItem;

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
public class MedicationResponse {

	private List<MedicationItem> medicationItems;

}
