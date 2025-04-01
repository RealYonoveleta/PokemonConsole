package com.yonoveleta.pokemon.trainer;

import java.util.List;
import java.util.Random;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonState;
import com.yonoveleta.pokemon.utils.RandomGenerator;

public class AITrainer extends AbstractTrainer {
	
	private static final Random rd = RandomGenerator.getInstance();

	public AITrainer(String name, List<Pokemon> pokemons) {
		super(name, pokemons);
	}
	
	private int chooseRandomPokemon() {
		int rdPokemon;

		do {
			rdPokemon = rd.nextInt(getPokemons().size());
		} while (getPokemons().get(rdPokemon).getState() == PokemonState.FAINTED);
		
		return rdPokemon;
	}

	@Override
	public void setActivePokemon(int option) {
		if(getHealthyPokemonCount() > 0)
			this.currentPokemon = chooseRandomPokemon();
	} 

}
