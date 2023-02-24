package com.drone.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "drone_battery_level_history")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DroneBatteryLevelHistory {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36, updatable = false)
	private String id;

	@Column(name = "serial_number", length = 100, updatable = false)
	private String serialNumber;

	@Column(name = "battery_level", updatable = false)
	private Integer batteryLevel;

	@Column(name = "date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date date;

}
