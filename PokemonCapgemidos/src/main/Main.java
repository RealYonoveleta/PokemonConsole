package main;

import java.util.ArrayList;
import java.util.List;

import battle.Battle;
import move.MoveRepository;
import pokemon.Pokemon;
import pokemon.PokemonFactory;
import trainer.Trainer;

public class Main {
	
	public static final PokemonFactory pokemonFactory = PokemonFactory.getInstance();
	public static final MoveRepository moveRepository = MoveRepository.getInstance();

	public static void main(String[] args) {
		List<Pokemon> pokemons1 = new ArrayList<Pokemon>();
		Pokemon pokemon = pokemonFactory.createPokemon("Charmander");
		pokemon.learnMove(moveRepository.getMove("flamethrower"));
		pokemons1.add(pokemon);
		pokemons1.add(pokemonFactory.createPokemon("Bulbasaur"));
		
		List<Pokemon> pokemons2 = new ArrayList<Pokemon>();
		pokemons2.add(pokemonFactory.createPokemon("Bulbasaur"));
		pokemons2.add(pokemonFactory.createPokemon("Ivysaur"));
		
		Trainer kikegu = new Trainer("Kikegu", pokemons1);
		Trainer luisja = new Trainer("Luisja", pokemons2);
		
		Battle battle = new Battle(kikegu, luisja);
		
		battle.start();	
	}

}
