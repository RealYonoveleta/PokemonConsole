package trainer;

import java.util.List;

import pokemon.Pokemon;

public interface Trainer {
	
	public void setActivePokemon();
	
	public int getHealthyPokemonCount();
	
	public Pokemon getCurrentPokemon();
	
	public List<Pokemon> getPokemons();
	
	public int getTeamSize();
	
	public String getName();

}
