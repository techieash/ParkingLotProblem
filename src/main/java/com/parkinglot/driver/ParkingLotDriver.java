package com.parkinglot.driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.parkinglot.commands.ICommand;
import com.parkinglot.service.CommandCenter;

public class ParkingLotDriver {
	

	public static void main(String[] args) {
		CommandCenter commandCenter = new CommandCenter();
		Map<String, ICommand> commands = commandCenter.getAvailableCommands();
		switch (args.length) {
		case 1:
			normalMode(args, commands);
			break;
		case 0:
			interactiveMode(commands);
			break;
		}

	}

	public static void interactiveMode(Map<String, ICommand> commands) {
		Scanner scanForInput = new Scanner(System.in);
		System.out.println("Please type 'exit' to quit");
		while (true) {
			String inputCommand = scanForInput.nextLine();
			if (inputCommand.equalsIgnoreCase("exit")) {
				break;
			}
			executeCommand(commands, inputCommand);
		}
		scanForInput.close();
	}

	public static void normalMode(String[] args, Map<String, ICommand> commands) {
		try (Stream<String> inputStrings = Files.lines(Paths.get(args[0]))) {

			inputStrings.forEach(input -> executeCommand(commands, input));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void executeCommand(Map<String, ICommand> commands, String line) {
		System.out.println(commands.get(line.split(" ")[0]).executeCommand(line));
	}
}
