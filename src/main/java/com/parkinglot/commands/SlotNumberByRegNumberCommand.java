package com.parkinglot.commands;

import static com.parkinglot.common.ParkingConstants.NOT_FOUND;

import java.util.Optional;

import com.parkinglot.common.InputParserUtil;
import com.parkinglot.entities.Slot;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class SlotNumberByRegNumberCommand implements ICommand {

	ParkingLotCacheRepository parkingLotCacheRepository;

	public SlotNumberByRegNumberCommand(ParkingLotCacheRepository parkingLotCacheRepository) {
		this.parkingLotCacheRepository = parkingLotCacheRepository;
	}

	@Override
	public String executeCommand(String commandInputString) {
		Optional<Slot> allotedSlotForARegNumber = Optional
				.ofNullable(parkingLotCacheRepository.getAllotedSlotForARegNumber(getRegNoOfACar(commandInputString)));
		if (allotedSlotForARegNumber.isPresent()) {
			return String.valueOf(allotedSlotForARegNumber.get().getSoltNumber());
		}
		return NOT_FOUND;
	}

	private String getRegNoOfACar(String commandInputString) {
		return InputParserUtil.splitBySpace(commandInputString)[1];
	}

}
