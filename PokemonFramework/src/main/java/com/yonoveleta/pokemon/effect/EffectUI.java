package com.yonoveleta.pokemon.effect;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.status.Status;

public class EffectUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	public void displayEffectAppliedMessage(Pokemon pokemon, Status status) {
		console.displayMessage(pokemon.getName() + " was inflicted with " + status);
	}

}
