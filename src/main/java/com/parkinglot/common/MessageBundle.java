package com.parkinglot.common;

import static com.parkinglot.common.ParkingConstants.LABELS;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageBundle {

	public static String getMessage(String commandKey, String message) {
		ResourceBundle messages = ResourceBundle.getBundle(LABELS);
		MessageFormat formatter = new MessageFormat("");
		
		formatter.applyPattern(messages.getString(commandKey));
		String output = formatter.format(new Object[] {message});
		return output;

	}

}
