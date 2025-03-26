package com.yonoveleta.pokemon.trainer;

import java.util.List;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonState;

public class PlayerTrainer extends AbstractTrainer {

	public PlayerTrainer(String name, List<Pokemon> pokemons) {
		super(name, pokemons);
	}
	
	public void setActivePokemon() {
		trainerUI.setActivePokemon(this);
	}
	
	public void setActivePokemon(int option) {
		if(!(getPokemons().get(option).getState() == PokemonState.FAINTED))
			this.currentPokemon = option;
		
		else setActivePokemon();
	}

}
