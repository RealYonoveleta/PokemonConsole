package com.yonoveleta.pokemon.io;


import java.util.Scanner;

public class ConsoleHandler {
	
	private static ConsoleHandler instance;
	private Scanner scanner;
	
	public ConsoleHandler() {
		scanner = new Scanner(System.in);
	}
	
	public static ConsoleHandler getInstance() {
		if(instance == null)
			instance = new ConsoleHandler();
		
		return instance;
	}
	
	public void displayMessage(String message, ConsoleColor color) {
		System.out.println(color + message + ConsoleColor.RESET);
	}
	
	public void displayMessage(String format, ConsoleColor color, Object... args) {
		System.out.printf(color + format + ConsoleColor.RESET, args);
	}
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
	public void displayMessage(String format, Object... args) {
		System.out.printf(format, args);
	}
	
	public void displayList(String title, String[] items) {
		System.out.println(title);
		for(int i = 0; i < items.length; i++) {
			System.out.println(i + 1 + ".- " + items[i]);
		}
	}
	
	public int askForChoice(String prompt, String... options) {
		return askForChoiceArray(prompt, options);
	}
	
	public int askForChoiceArray(String prompt, String[] options) {
		displayList(prompt, options);
		displayMessage("%n%s: ", "Choose");
		
		while(true) {
			try {
				int choice = scanner.nextInt();
				if(choice >= 1 && choice <= options.length) {
					return choice - 1;
				} else {
					displayMessage("Please select a valid option.");
				}
			} catch(NumberFormatException e) {
				displayMessage("Please enter a number.");
			}
			
		}
	}
	
	public int askForNumber(String prompt) {
		System.out.println(prompt);
		while(true) {
			try {
				return scanner.nextInt();
			} catch(NumberFormatException e) {
				displayMessage("Invalid number, try again,");
			}
		}
	}
	
	public int askForNumber(String prompt, Object... args) {
		String formattedPrompt = String.format(prompt, args);
		return askForNumber(formattedPrompt);
	}
	
	public void close() {
		scanner.close();
	}

}
