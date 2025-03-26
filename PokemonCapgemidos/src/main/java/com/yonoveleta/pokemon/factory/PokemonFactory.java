package com.yonoveleta.pokemon.factory;

import java.io.FileNotFoundException;
import java.util.List;

import com.yonoveleta.pokemon.di.annotation.Logic;
import com.yonoveleta.pokemon.parse.PokemonParser;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonImpl;

@Logic
public class PokemonFactory extends AbstractGameFactory<String, Pokemon> {

	private static final PokemonParser pokemonParser = new PokemonParser("data/pokemons.json");
	private static final PokemonFactory INSTANCE = new PokemonFactory();

	private PokemonFactory() {
		super();
	}

	public static PokemonFactory getInstance() {
		return INSTANCE;
	}

	@Override
	protected List<Pokemon> loadData() throws FileNotFoundException {
		return pokemonParser.parse();
	}

	@Override
	public String getKey(Pokemon pokemon) {
		return pokemon.getName();
	}

	@Override
	protected Pokemon createCopy(Pokemon pokemon) {
		return new PokemonImpl(pokemon); 
	}

	
}
