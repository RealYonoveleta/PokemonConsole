package trainer;

import java.util.List;
import java.util.Scanner;

import pokemon.Pokemon;
import pokemon.PokemonState;

public class Trainer {
	
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
	
	public void setActivePokemon(int pokemon) {
		this.currentPokemon = pokemon;
	}

	public void setActivePokemon(Scanner userInput) {
		System.out.printf("Pokemons: \n");
		int option = 1;
		for(Pokemon pokemon : getPokemons()) {
			System.out.printf("%d.- %s hp:%d\n", option, pokemon.getName(), pokemon.getHp());
			option++;
		}
		
		int userOption;
		Pokemon selected;
		
		do {
			System.out.println("Which pokemon you choose?: ");
			userOption = userInput.nextInt() - 1;
			selected = getPokemons().get(userOption);
		} while(selected.getState() == PokemonState.FAINTED);
		
		this.currentPokemon = userOption;	
	}
	
	public int healthyPokemonCount() {
		int count = 0;
		for(Pokemon pokemon : getPokemons()) {
			if(pokemon.getState() != PokemonState.FAINTED) {
				count++;
			}
		}
		
		return count;
	}

}
