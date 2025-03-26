package com.yonoveleta.pokemon.trainer;

import java.util.List;

import com.yonoveleta.pokemon.pokemon.Pokemon;

public interface Trainer {
	
	public void setActivePokemon();
	
	public int getHealthyPokemonCount();
	
	public Pokemon getCurrentPokemon();
	
	public List<Pokemon> getPokemons();
	
	public int getTeamSize();
	
	public String getName();

}
