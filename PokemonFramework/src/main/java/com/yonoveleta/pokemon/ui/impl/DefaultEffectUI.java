package com.yonoveleta.pokemon.ui.impl;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.status.Status;
import com.yonoveleta.pokemon.ui.EffectUI;

public class DefaultEffectUI implements EffectUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	public void displayEffectAppliedMessage(Pokemon pokemon, Status status) {
		console.displayMessage(pokemon.getName() + " was inflicted with " + status);
	}

}
