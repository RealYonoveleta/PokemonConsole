package trainer;

import java.util.List;
import java.util.Random;

import pokemon.Pokemon;
import pokemon.PokemonState;
import utils.RandomGenerator;

public class Trainer {

	private static final TrainerUI trainerUI = new TrainerUI();
	private static final Random rd = RandomGenerator.getInstance();

	private String name;
	private List<Pokemon> pokemons;
	private int currentPokemon = 0;

	public Trainer(String name, List<Pokemon> pokemons) {
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

	public void setActivePokemon() {
		trainerUI.setActivePokemon(this);
	}
	
	public void setActivePokemon(int option) {
		this.currentPokemon = option;
	}

	public int healthyPokemonCount() {
		int count = 0;
		for (Pokemon pokemon : getPokemons()) {
			if (pokemon.getState() != PokemonState.FAINTED) {
				count++;
			}
		}

		return count;
	}

	public void chooseRandomPokemon() {
		int rdPokemon;

		do {
			rdPokemon = rd.nextInt(getPokemons().size());
		} while (getPokemons().get(rdPokemon).getState() == PokemonState.FAINTED);

		setActivePokemon(rdPokemon);
	}

	public void chooseRandomMove(Pokemon target) {
		int numberOfMoves = getCurrentPokemon().movesKnown();
		if (numberOfMoves > 0) {
			getCurrentPokemon().useMove(rd.nextInt(getCurrentPokemon().getMoveset().size()), target);
		}
	}

}
