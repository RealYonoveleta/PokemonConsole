package trainer;

import java.util.List;

import pokemon.Pokemon;
import pokemon.PokemonState;

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
