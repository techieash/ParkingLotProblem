package com.parkinglot.entities;

public class Slot {

	private int slotNumber;

	public Slot(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public int getSoltNumber() {
		return slotNumber;
	}

	public void setSoltNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + slotNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		if (slotNumber != other.slotNumber)
			return false;
		return true;
	}
}
