package trainer;

import java.util.List;

import pokemon.Pokemon;
import pokemon.PokemonState;

public abstract class AbstractTrainer implements Trainer {

	protected static final TrainerUI trainerUI = new TrainerUI();

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

}
