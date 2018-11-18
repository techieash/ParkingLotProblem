package com.parkinglot.commands;

import java.util.stream.Collectors;

import com.parkinglot.common.InputParserUtil;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class SlotNumbersForCarsByColorCommand implements ICommand {

	ParkingLotCacheRepository parkingLotCacheRepository;

	public SlotNumbersForCarsByColorCommand(ParkingLotCacheRepository parkingLotCacheRepository) {
		this.parkingLotCacheRepository = parkingLotCacheRepository;
	}

	@Override
	public String executeCommand(String commandInputString) {
		String slotNumbers = parkingLotCacheRepository.getSlotNumbersForAColor(getCarColor(commandInputString)).stream()
				.map(slot -> String.valueOf(slot.getSoltNumber())).collect(Collectors.joining(","));
		return slotNumbers;
	}

	private String getCarColor(String commandInputString) {
		return InputParserUtil.splitBySpace(commandInputString)[1];
	}

}
