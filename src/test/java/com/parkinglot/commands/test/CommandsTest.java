package com.parkinglot.commands.test;

import static com.parkinglot.common.ParkingConstants.COLOR;
import static com.parkinglot.common.ParkingConstants.NEW_LINE_DELIMETER;
import static com.parkinglot.common.ParkingConstants.SLOT_NO;
import static com.parkinglot.common.ParkingConstants.TAB_DELIMETER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.parkinglot.commands.CreateParkingSlotsCommand;
import com.parkinglot.commands.ParkCarCommand;
import com.parkinglot.commands.RegNumberForCarsByColorCommand;
import com.parkinglot.commands.SlotNumberByRegNumberCommand;
import com.parkinglot.commands.SlotNumbersForCarsByColorCommand;
import com.parkinglot.commands.StatusCommand;
import com.parkinglot.commands.UnParkCarCommand;
import com.parkinglot.common.ParkingConstants;
import com.parkinglot.entities.Car;
import com.parkinglot.entities.Slot;
import com.parkinglot.repository.ParkingLotCacheRepository;

@RunWith(MockitoJUnitRunner.class)
public class CommandsTest {

	@InjectMocks
	CreateParkingSlotsCommand createParking;

	@InjectMocks
	ParkCarCommand parkCar;

	@InjectMocks
	StatusCommand status;

	@InjectMocks
	UnParkCarCommand unparkCar;

	@InjectMocks
	RegNumberForCarsByColorCommand regNoQuery;

	@InjectMocks
	SlotNumberByRegNumberCommand slotNumberForRegNoQuery;

	@InjectMocks
	SlotNumbersForCarsByColorCommand slotNumbersForCarsQuery;

	@Mock
	ParkingLotCacheRepository parkingLotCacheRepository;

	@Test
	public void createParkingSlots() throws Exception {
		Mockito.when(parkingLotCacheRepository.getSlotsHolder()).thenReturn(new PriorityQueue<>());
		String actual = createParking.executeCommand("create_parking_lot 6");
		Assert.assertEquals("Created a parking lot with 6 slots", actual);
	}

	@Test
	public void providedCarDetailsThenPark() throws Exception {
		Mockito.when(parkingLotCacheRepository.getFreeSlot()).thenReturn(1);
		String actual = parkCar.executeCommand("park KA-01-HH-1234 White");
		Assert.assertEquals("Allocated slot number: 1", actual);
	}

	@Test
	public void parkingFullForNoSpace() throws Exception {
		Mockito.when(parkingLotCacheRepository.getFreeSlot()).thenReturn(-1);
		String actual = parkCar.executeCommand("park KA-01-HH-1234 White");
		Assert.assertEquals("Sorry, parking lot is full", actual);
	}

	@Test
	public void unparkCar() throws Exception {
		String actual = unparkCar.executeCommand("leave 4");
		Assert.assertEquals("Slot number 4 is free", actual);
	}

	@Test
	public void provideParkingLotStatus() throws Exception {
		Map<Slot, Car> carForSlot = new HashMap<>();
		carForSlot.put(new Slot(1), getCar("White", "KA-01-HH-1234"));
		Mockito.when(parkingLotCacheRepository.getCarForSlot()).thenReturn(carForSlot);
		String actual = status.executeCommand("status");
		StringBuilder table = new StringBuilder();
		table.append(SLOT_NO).append(TAB_DELIMETER).append(ParkingConstants.REGISTRATION_NO).append(TAB_DELIMETER)
				.append(COLOR).append(NEW_LINE_DELIMETER);
		table.append(1).append(TAB_DELIMETER).append("KA-01-HH-1234").append(TAB_DELIMETER).append("White")
				.append(NEW_LINE_DELIMETER);
		Assert.assertEquals(table.toString(), actual);
	}

	@Test
	public void givenColorProvideRegNumber() throws Exception {
		List<Car> cars = new ArrayList<>();
		cars.add(getCar("White", "KA-01-HH-1234"));
		Mockito.when(parkingLotCacheRepository.getListOfCarsForAColor("White")).thenReturn(cars);
		String actual = regNoQuery.executeCommand("registration_numbers_for_cars_with_colour White");
		Assert.assertEquals("KA-01-HH-1234", actual);
	}

	@Test
	public void givenRegNoProvideSlotNumber() throws Exception {
		Mockito.when(parkingLotCacheRepository.getAllotedSlotForARegNumber("KA-01-HH-3141")).thenReturn(getSlot(4));
		String actual = slotNumberForRegNoQuery.executeCommand("slot_number_for_registration_number KA-01-HH-3141");
		Assert.assertEquals("4", actual);
	}
	
	@Test
	public void givenRegNoReturnNotFound() throws Exception {
		Mockito.when(parkingLotCacheRepository.getAllotedSlotForARegNumber("KA-01-HH-3141")).thenReturn(null);
		String actual = slotNumberForRegNoQuery.executeCommand("slot_number_for_registration_number KA-01-HH-3141");
		Assert.assertEquals("Not found", actual);
	}

	@Test
	public void givenColorProvideSlotNumbers() throws Exception {
		List<Slot> slots = new ArrayList<>();
		slots.add(getSlot(4));
		slots.add(getSlot(6));
		Mockito.when(parkingLotCacheRepository.getSlotNumbersForAColor("White")).thenReturn(slots);
		String actual = slotNumbersForCarsQuery.executeCommand("slot_numbers_for_cars_with_colour White");
		Assert.assertEquals("4,6", actual);
	}

	private Slot getSlot(int slotNumber) {
		return new Slot(slotNumber);
	}

	private Car getCar(String color, String regNo) {
		Car car = new Car();
		car.setColor(color);
		car.setRegNo(regNo);
		return car;
	}

}
