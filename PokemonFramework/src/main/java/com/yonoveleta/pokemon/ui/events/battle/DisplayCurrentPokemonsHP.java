package com.yonoveleta.pokemon.ui.events.battle;

import com.yonoveleta.pokemon.event.Event;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;

public class DisplayCurrentPokemonsHP implements Event {

	private final Trainer trainer1, trainer2;
	private final Pokemon pokemon1, pokemon2;

	public DisplayCurrentPokemonsHP(Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
	}

	public Trainer getTrainer1() {
		return trainer1;
	}

	public Trainer getTrainer2() {
		return trainer2;
	}

	public Pokemon getPokemon1() {
		return pokemon1;
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}

}
