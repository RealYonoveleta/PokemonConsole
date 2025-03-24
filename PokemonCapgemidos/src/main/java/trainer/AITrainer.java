package trainer;

import java.util.List;
import java.util.Random;

import pokemon.Pokemon;
import pokemon.PokemonState;
import utils.RandomGenerator;

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
	public void setActivePokemon() {
		if(getHealthyPokemonCount() > 0)
			this.currentPokemon = chooseRandomPokemon();
	} 

}
