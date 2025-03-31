package com.yonoveleta.pokemon.ui.impl;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.status.Status;
import com.yonoveleta.pokemon.ui.StatusUI;

public class DefaultStatusUI implements StatusUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	public void displayRemovedStatus(Pokemon pokemon, Status status) {
		console.displayMessage("%n%s is no longer %s!", pokemon.getName(), status.getName());
	}
	
}
