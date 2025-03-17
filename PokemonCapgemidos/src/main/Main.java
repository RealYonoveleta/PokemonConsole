package main;

import java.util.ArrayList;
import java.util.List;

import battle.Battle;
import move.Move;
import move.MoveRepository;
import pokemon.Pokemon;
import pokemon.PokemonFactory;
import trainer.Trainer;

public class Main {

	public static void main(String[] args) {
		
		
		List<Pokemon> pokemons1 = new ArrayList<Pokemon>();
		Pokemon bulbasaur = PokemonFactory.createPokemon("Bulbasaur");
		Move absorb = MoveRepository.getMove("absorb");
		
		bulbasaur.learnMove(absorb);
		bulbasaur.learnMove(absorb);
		bulbasaur.learnMove(absorb);
		bulbasaur.learnMove(absorb);
		bulbasaur.learnMove(absorb);
		
		pokemons1.add(bulbasaur);
		
		List<Pokemon> pokemons2 = new ArrayList<Pokemon>();
		pokemons2.add(PokemonFactory.createPokemon("Bulbasaur"));
		pokemons2.add(PokemonFactory.createPokemon("Ivysaur"));
		
		Trainer kikegu = new Trainer("Kikegu", pokemons1);
		Trainer luisja = new Trainer("Luisja", pokemons2);
		
		Battle battle = new Battle(kikegu, luisja);
		
		battle.start();	
	}

}
