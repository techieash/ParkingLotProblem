package com.parkinglot.service;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.commands.CreateParkingSlotsCommand;
import com.parkinglot.commands.ICommand;
import com.parkinglot.commands.ParkCarCommand;
import com.parkinglot.commands.RegNumberForCarsByColorCommand;
import com.parkinglot.commands.SlotNumberByRegNumberCommand;
import com.parkinglot.commands.SlotNumbersForCarsByColorCommand;
import com.parkinglot.commands.StatusCommand;
import com.parkinglot.commands.UnParkCarCommand;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class CommandCenter {

	private static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER = "slot_number_for_registration_number";
	private static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOUR = "slot_numbers_for_cars_with_colour";
	private static final String REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR = "registration_numbers_for_cars_with_colour";
	private static final String STATUS = "status";
	private static final String LEAVE = "leave";
	private static final String PARK = "park";
	private static final String CREATE_PARKING_LOT = "create_parking_lot";

	public Map<String, ICommand> getAvailableCommands() {
		Map<String, ICommand> commands = new HashMap<>();
		ParkingLotCacheRepository parkingDetailsCache = new ParkingLotCacheRepository();
		commands.put(CREATE_PARKING_LOT, new CreateParkingSlotsCommand(parkingDetailsCache));
		commands.put(PARK, new ParkCarCommand(parkingDetailsCache));
		commands.put(LEAVE, new UnParkCarCommand(parkingDetailsCache));
		commands.put(STATUS, new StatusCommand(parkingDetailsCache));
		commands.put(REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR,
				new RegNumberForCarsByColorCommand(parkingDetailsCache));
		commands.put(SLOT_NUMBERS_FOR_CARS_WITH_COLOUR, new SlotNumbersForCarsByColorCommand(parkingDetailsCache));
		commands.put(SLOT_NUMBER_FOR_REGISTRATION_NUMBER, new SlotNumberByRegNumberCommand(parkingDetailsCache));
		return commands;

	}

}
