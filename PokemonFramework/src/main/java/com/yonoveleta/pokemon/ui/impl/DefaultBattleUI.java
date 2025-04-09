package com.yonoveleta.pokemon.ui.impl;

import java.util.List;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.ui.BattleUI;
import com.yonoveleta.pokemon.ui.events.battle.BattleUIEventHandler;

public class DefaultBattleUI implements BattleUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();

	{
		new BattleUIEventHandler(this);
	}

	public void displayBattleStart() {
		console.displayMessage("\nThe battle begins!");
	}

	public void showPokemonStates(List<Trainer> participants) {
		String pokemonStates = "";
		for (Trainer participant : participants) {
			Pokemon participantPokemon = participant.getCurrentPokemon();
			pokemonStates += String.format("%s(%s Lv%d): %dhp\t", participant.getName(), participantPokemon.getName(),
					participantPokemon.getLevel(), participantPokemon.getHp());
		}
		
		console.displayMessage(pokemonStates);
	}

	public void announceFaint(Pokemon pokemon) {
		console.displayMessage("\n%s fainted...\n\n", pokemon.getName());
	}

	public void announceWinner(Trainer winner) {
		console.displayMessage("%n%s won!%n%n", winner.getName());
	}

}
