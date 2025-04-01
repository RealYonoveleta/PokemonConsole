package com.yonoveleta.pokemon.trainer;

import java.util.List;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.ui.TrainerUI;

public interface Trainer {
	
	public void setActivePokemon(int option);
	
	public int getHealthyPokemonCount();
	
	public Pokemon getCurrentPokemon();
	
	public List<Pokemon> getPokemons();
	
	public int getTeamSize();
	
	public String getName();
	
	void setTrainerUI(TrainerUI UI);

}
