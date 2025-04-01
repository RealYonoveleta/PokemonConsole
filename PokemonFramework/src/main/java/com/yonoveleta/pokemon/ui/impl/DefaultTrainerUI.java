package com.yonoveleta.pokemon.ui.impl;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.ui.TrainerUI;

public class DefaultTrainerUI implements TrainerUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();

	public void setActivePokemon(Trainer trainer) {
		int option = console.askForChoice("Pokemons: ",
				trainer.getPokemons().stream().map(pokemon -> String.format("%-15s hp:%-6d", pokemon.getName(), pokemon.getHp()))
						.toArray(String[]::new));
		
		trainer.setActivePokemon(option);
	}

}
