package com.drone.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Message {
	
	DRONE_FOUND("Drone Found"),
	DRONE_NOT_FOUND("Drone Not Found");
	
	private String description;


}
