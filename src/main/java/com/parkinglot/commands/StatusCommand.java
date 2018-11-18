package com.parkinglot.commands;

import static com.parkinglot.common.ParkingConstants.COLOR;
import static com.parkinglot.common.ParkingConstants.NEW_LINE_DELIMETER;
import static com.parkinglot.common.ParkingConstants.SLOT_NO;
import static com.parkinglot.common.ParkingConstants.TAB_DELIMETER;

import java.util.Map;

import com.parkinglot.common.ParkingConstants;
import com.parkinglot.entities.Car;
import com.parkinglot.entities.Slot;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class StatusCommand implements ICommand {

	ParkingLotCacheRepository parkingLotCacheRepository;

	public StatusCommand(ParkingLotCacheRepository parkingLotCacheRepository) {
		this.parkingLotCacheRepository = parkingLotCacheRepository;
	}

	@Override
	public String executeCommand(String commandInputString) {
		Map<Slot, Car> carForSlot = parkingLotCacheRepository.getCarForSlot();
		StringBuilder table = new StringBuilder();
		table.append(SLOT_NO).append(TAB_DELIMETER).append(ParkingConstants.REGISTRATION_NO).append(TAB_DELIMETER).append(COLOR).append(NEW_LINE_DELIMETER);
		carForSlot.forEach((slot, car) -> table.append(slot.getSoltNumber()).append(TAB_DELIMETER).append(car.getRegNo())
				.append(TAB_DELIMETER).append(car.getColor()).append(NEW_LINE_DELIMETER));
		return table.toString();
	}

}
