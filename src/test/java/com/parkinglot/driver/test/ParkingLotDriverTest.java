package com.parkinglot.driver.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.parkinglot.driver.ParkingLotDriver;

@RunWith(MockitoJUnitRunner.class)
public class ParkingLotDriverTest {

	
	@Test
	public void givenFileNameThenFindParkingSlots() throws Exception {
		ParkingLotDriver.main(new String[] {"file_inputs.txt"});
	}
	
}
