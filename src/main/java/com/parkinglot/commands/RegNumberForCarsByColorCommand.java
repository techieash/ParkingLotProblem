package com.parkinglot.commands;

import java.util.stream.Collectors;

import com.parkinglot.common.InputParserUtil;
import com.parkinglot.repository.ParkingLotCacheRepository;

public class RegNumberForCarsByColorCommand implements ICommand {

	ParkingLotCacheRepository parkingLotCacheRepository;

	public RegNumberForCarsByColorCommand(ParkingLotCacheRepository parkingLotCacheRepository) {
		this.parkingLotCacheRepository = parkingLotCacheRepository;
	}

	@Override
	public String executeCommand(String commandInputString) {
		String regNumbers = parkingLotCacheRepository.getListOfCarsForAColor(getCarColor(commandInputString)).stream()
				.map(car -> car.getRegNo()).collect(Collectors.joining(","));
		return regNumbers;
	}

	private String getCarColor(String commandInputString) {
		return InputParserUtil.splitBySpace(commandInputString)[1];
	}

}
