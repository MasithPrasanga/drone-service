package com.drone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.drone.controller.request.DroneRequest;
import com.drone.controller.response.DroneResponse;
import com.drone.exception.ExistingDroneException;
import com.drone.model.Drone;
import com.drone.repository.DroneRepository;
import com.drone.service.DroneService;
import com.drone.service.Transformer;

@SpringBootTest
class DroneServiceApplicationTests {

	@Autowired
	private DroneService droneService;

	@MockBean
	private DroneRepository droneRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testDroneRegistrationSuccess() {
		// Arrange
		DroneRequest droneRequest = TestDataPopulator.createDroneRequest();
		Mockito.when(droneRepository.findDroneBySerialNumber(Mockito.anyString())).thenReturn(null);
		Drone drone = Transformer.createDrone(droneRequest);
		Mockito.when(droneRepository.save(Mockito.any(Drone.class))).thenReturn(drone);

		// Act
		DroneResponse response = droneService.registerDrone(droneRequest);

		// Assert
		assertNotNull(response);
		assertEquals(drone.getSerialNumber(), response.getSerialNumber());
	}

	@Test
	public void testRegisterExistingDrone() {
		// Create a DroneRequest object with a valid serialNumber
		DroneRequest droneRequest = TestDataPopulator.createDroneRequest();

		// Create a mock Drone object with the same serialNumber as the DroneRequest
		Drone existingDrone = new Drone();
		existingDrone.setSerialNumber("fff8836e-5ee4-46d6-b1b9-65aafad6a940");

		// Mock the behavior of the droneRepository to return the existing Drone object
		when(droneRepository.findDroneBySerialNumber(droneRequest.getSerialNumber())).thenReturn(existingDrone);

		// Verify that the method throws the correct exception with the correct error message and HTTP status code
		Exception exception = assertThrows(ExistingDroneException.class, () -> {
			droneService.registerDrone(droneRequest);
		});

		String expectedMessage = "drone has already been created for the [ fff8836e-5ee4-46d6-b1b9-65aafad6a940 ] serial number";
		String actualMessage = exception.getMessage();

		assertEquals(HttpStatus.BAD_REQUEST, ((ExistingDroneException) exception).getHttpStatusCode());
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
