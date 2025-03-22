package main;

import cache.MoveCache;
import move.Move;
import move.MoveRepository;
import pokemon.PokemonFactory;

public class Main {
	
	public static final PokemonFactory pokemonFactory = PokemonFactory.getInstance();
	public static final MoveRepository moveRepository = MoveRepository.getInstance();

	public static void main(String[] args) {
		
		MoveCache moves = MoveCache.getInstance();
		
		for(Move move : moves.getAllMoves()) {
			System.out.println(move);
		}
		
		/*List<Pokemon> pokemons1 = new ArrayList<Pokemon>();
		Pokemon pokemon = pokemonFactory.createPokemon("Charmander");
		Move move = moveRepository.getMove("Ember");
		move.addEffect(new StatusEffect(new Flinch(), 1));
		move.addEffect(new StatusEffect(new Burn(), 1));
		pokemon.learnMove(move);
		pokemons1.add(pokemon);
		
		List<Pokemon> pokemons2 = new ArrayList<Pokemon>();
		pokemons2.add(pokemonFactory.createPokemon("Bulbasaur"));
		pokemons2.add(pokemonFactory.createPokemon("squirtle"));
		
		AbstractTrainer kikegu = new PlayerTrainer("Kikegu", pokemons1);
		AbstractTrainer luisja = new AITrainer("Luisja", pokemons2);
		
		Battle battle = new Battle(kikegu, luisja);
		
		battle.start();*/
	}

}
