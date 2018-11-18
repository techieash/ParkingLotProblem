package com.parkinglot.commands;

import static com.parkinglot.common.ParkingConstants.CREATE_TEMPLATE;

import com.parkinglot.common.InputParserUtil;
import com.parkinglot.common.MessageBundle;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class CreateParkingSlotsCommand implements ICommand {


	ParkingLotCacheRepository parkingLotCacheRepository;

	public CreateParkingSlotsCommand(ParkingLotCacheRepository parkingLotCacheRepository) {
		this.parkingLotCacheRepository = parkingLotCacheRepository;
	}

	@Override
	public String executeCommand(String commandInputString) {
		int slotNumber = getNoOfSlotsToCreate(commandInputString);
		for (int i = 1; i <= slotNumber; i++) {
			parkingLotCacheRepository.getSlotsHolder().add(i);
		}
		return MessageBundle.getMessage(CREATE_TEMPLATE, String.valueOf(slotNumber));
	}

	private int getNoOfSlotsToCreate(String commandInputString) {
		return Integer.valueOf(InputParserUtil.splitBySpace(commandInputString)[1]);
	}

}
