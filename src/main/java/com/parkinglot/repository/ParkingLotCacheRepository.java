package com.parkinglot.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.parkinglot.entities.Car;
import com.parkinglot.entities.Slot;

public class ParkingLotCacheRepository {

	private Map<Slot, Car> allotedSlotForACar;
	private Map<String, List<Car>> listOfCarsForAColor;
	private Map<String, Slot> allotedSlotForARegNumber;
	private PriorityQueue<Integer> slotsHolder;

	public ParkingLotCacheRepository() {
		allotedSlotForACar = new HashMap<Slot, Car>();
		listOfCarsForAColor = new HashMap<>();
		allotedSlotForARegNumber = new HashMap<>();
		slotsHolder = new PriorityQueue<Integer>();
	}

	public Map<Slot, Car> getCarForSlot() {
		return allotedSlotForACar;
	}

	public List<Car> getListOfCarsForAColor(String color) {
		return listOfCarsForAColor.get(color);
	}

	public Slot getAllotedSlotForARegNumber(String regNo) {
		return allotedSlotForARegNumber.get(regNo);
	}

	public int getFreeSlot() {
		if (slotsHolder.size() > 0) {
			return slotsHolder.poll();
		} else
			return -1;

	}

	public PriorityQueue<Integer> getSlotsHolder() {
		return slotsHolder;
	}

	public List<Slot> getSlotNumbersForAColor(String color) {
		List<Car> cars = getListOfCarsForAColor(color);
		return cars.stream().map(car -> allotedSlotForARegNumber.get(car.getRegNo())).collect(Collectors.toList());

	}

	public void createParking(Slot slot, Car car) {
		registerSlotForACar(slot, car);
		addCarsForAColor(car);
		registerSlotForARegNumber(car.getRegNo(), slot);
	}

	public void removeCarFrom(Slot slot) {
		Car car = allotedSlotForACar.get(slot);
		allotedSlotForACar.remove(slot);
		List<Car> cars = listOfCarsForAColor.get(car.getColor());
		cars.remove(car);
		allotedSlotForARegNumber.remove(car.getRegNo());
		slotsHolder.add(slot.getSoltNumber());

	}

	private void registerSlotForACar(Slot slot, Car car) {
		allotedSlotForACar.put(slot, car);
	}

	private void addCarsForAColor(Car car) {
		List<Car> list = listOfCarsForAColor.get(car.getColor());
		if (list == null) {
			List<Car> cars = new ArrayList<>();
			cars.add(car);
			listOfCarsForAColor.put(car.getColor(), cars);
		} else {
			list.add(car);
		}
	}

	private void registerSlotForARegNumber(String regNo, Slot slot) {
		allotedSlotForARegNumber.put(regNo, slot);
	}

}
