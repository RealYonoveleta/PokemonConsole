package com.yonoveleta.pokemon.ui.impl;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.ui.TrainerUI;
import com.yonoveleta.pokemon.ui.events.trainer.TrainerUIEventHandler;

public class DefaultTrainerUI implements TrainerUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	{
		new TrainerUIEventHandler(this);
	}

	public void setActivePokemon(Trainer trainer) {
		int option = console.askForChoice("Pokemons: ",
				trainer.getPokemons().stream().map(pokemon -> String.format("%-15s hp:%-6d", pokemon.getName(), pokemon.getHp()))
						.toArray(String[]::new));
		
		trainer.setActivePokemon(option);
	}

	@Override
	public int askForMoveChoice(Pokemon pokemon) {
		StringBuilder prompt = new StringBuilder();
		prompt.append(String.format("\nWhat should %s do?:%n%n", pokemon.getName()));
		prompt.append(String.format("%-3s %-15s %-10s %-6s %-10s%n", "#", "Move", "Power", "PPs", "Type"));
		prompt.append("--------------------------------------------");

		return console.askForChoiceArray(prompt.toString(),
				pokemon.getMoveset().stream().map(Move::toString).toArray(String[]::new));
	}

}
