package com.parkinglot.commands;

import static com.parkinglot.common.ParkingConstants.PARK_FULL;
import static com.parkinglot.common.ParkingConstants.PARK_TEMPLATE;

import com.parkinglot.common.InputParserUtil;
import com.parkinglot.common.MessageBundle;
import com.parkinglot.entities.Car;
import com.parkinglot.entities.Slot;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class ParkCarCommand implements ICommand {

	ParkingLotCacheRepository parkingLotCacheRepository;

	public ParkCarCommand(ParkingLotCacheRepository parkingLotCacheRepository) {
		this.parkingLotCacheRepository = parkingLotCacheRepository;
	}

	@Override
	public String executeCommand(String commandInputString) {
		int slotId = parkingLotCacheRepository.getFreeSlot();
		if (!isSlotAvailable(slotId)) {
			return MessageBundle.getMessage(PARK_FULL, "");
		}
		Slot slot = new Slot(slotId);
		Car car = getCar(commandInputString);
		parkingLotCacheRepository.createParking(slot, car);
		return MessageBundle.getMessage(PARK_TEMPLATE, String.valueOf(slotId));

	}

	private boolean isSlotAvailable(int slotId) {
		return slotId > 0;
	}

	private Car getCar(String input) {
		String[] parsedInput = InputParserUtil.splitBySpace(input);
		Car car = new Car();
		car.setColor(parsedInput[2]);
		car.setRegNo(parsedInput[1]);
		return car;
	}

}
