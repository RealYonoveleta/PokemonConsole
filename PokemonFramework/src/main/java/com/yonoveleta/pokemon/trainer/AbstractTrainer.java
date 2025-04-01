package com.yonoveleta.pokemon.trainer;

import java.util.List;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonState;
import com.yonoveleta.pokemon.ui.TrainerUI;
import com.yonoveleta.pokemon.ui.manager.TrainerUIManager;

public abstract class AbstractTrainer implements Trainer {

	protected TrainerUI trainerUI = TrainerUIManager.getInstance().getUI();

	protected String name;
	protected List<Pokemon> pokemons;
	protected int currentPokemon = 0;

	public AbstractTrainer(String name, List<Pokemon> pokemons) {
		this.name = name;
		this.pokemons = pokemons;
	}

	public List<Pokemon> getPokemons() {
		return this.pokemons;
	}

	public Pokemon getCurrentPokemon() {
		return this.pokemons.get(currentPokemon);
	}

	public int getTeamSize() {
		return this.pokemons.size();
	}

	public String getName() {
		return name;
	}

	public int getHealthyPokemonCount() {
		int count = 0;
		for (Pokemon pokemon : getPokemons()) {
			if (pokemon.getState() != PokemonState.FAINTED) {
				count++;
			}
		}

		return count;
	}
	
	@Override
	public void setTrainerUI(TrainerUI trainerUI) {
		this.trainerUI = trainerUI;
	}

}
