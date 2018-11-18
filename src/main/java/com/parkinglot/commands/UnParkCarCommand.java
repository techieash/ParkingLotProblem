package com.parkinglot.commands;

import static com.parkinglot.common.ParkingConstants.UNPARK_TEMPLATE;

import com.parkinglot.common.InputParserUtil;
import com.parkinglot.common.MessageBundle;
import com.parkinglot.entities.Slot;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class UnParkCarCommand implements ICommand {

	ParkingLotCacheRepository parkingLotCacheRepository;

	public UnParkCarCommand(ParkingLotCacheRepository parkingLotCacheRepository) {
		this.parkingLotCacheRepository = parkingLotCacheRepository;
	}

	@Override
	public String executeCommand(String commandInputString) {
		Slot slot = new Slot(getSlotNumber(commandInputString));
		parkingLotCacheRepository.removeCarFrom(slot);
		return MessageBundle.getMessage(UNPARK_TEMPLATE, String.valueOf(slot.getSoltNumber()));
	}

	private int getSlotNumber(String commandInputString) {
		return Integer.valueOf(InputParserUtil.splitBySpace(commandInputString)[1]);
	}

}
