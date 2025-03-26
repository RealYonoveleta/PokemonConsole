package com.yonoveleta.pokemon.status;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.pokemon.Pokemon;

public class StatusUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	public void displayRemovedStatus(Pokemon pokemon, Status status) {
		console.displayMessage("%n%s is no longer %s!", pokemon.getName(), status.getName());
	}
	
}
