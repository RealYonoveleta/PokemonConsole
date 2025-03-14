package main;

import java.util.ArrayList;
import java.util.List;

import battle.Battle;
import move.Move;
import move.Moves;
import pokemon.Pokemon;
import pokemon.PokemonImpl;
import trainer.Trainer;

public class Main {

	public static void main(String[] args) {

		List<Move> moveset = new ArrayList<Move>();
		moveset.add(Moves.ABSORB);
		moveset.add(Moves.FLAMETHROWER);
		
		List<Pokemon> pokemons1 = new ArrayList<Pokemon>();
		pokemons1.add(new PokemonImpl("pokemon1", 300, moveset));
		pokemons1.add(new PokemonImpl("pokemon2", 80, moveset));
		
		List<Pokemon> pokemons2 = new ArrayList<Pokemon>();
		pokemons2.add(new PokemonImpl("pokemon3", 300, moveset));
		pokemons2.add(new PokemonImpl("pokemon4", 80, moveset));
		
		Trainer kikegu = new Trainer("Kikegu", pokemons1);
		Trainer luisja = new Trainer("Luisja", pokemons2);
		
		Battle battle = new Battle(kikegu, luisja);
		
		battle.start();
		
	}

}
