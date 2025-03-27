package com.yonoveleta.pokemon.factory;

import java.util.List;

import com.yonoveleta.pokemon.data.DataPathProvider;
import com.yonoveleta.pokemon.di.annotation.Factory;
import com.yonoveleta.pokemon.parse.PokemonParser;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonImpl;

@Factory
public class PokemonFactory extends AbstractGameFactory<String, Pokemon> {

	private static final PokemonParser pokemonParser = new PokemonParser(DataPathProvider.POKEMON_DATA);
	private static final PokemonFactory INSTANCE = new PokemonFactory();

	private PokemonFactory() {
		super();
	}

	public static PokemonFactory getInstance() {
		return INSTANCE;
	}

	@Override
	protected List<Pokemon> loadData() {
		return pokemonParser.parse();
	}

	@Override
	public String getKey(Pokemon pokemon) {
		return pokemon.getName().toLowerCase();
	}

	@Override
	protected Pokemon createCopy(Pokemon pokemon) {
		return new PokemonImpl(pokemon); 
	}

	@Override
	protected String getKeyFormat(String key) {
		return key.toLowerCase();
	}
	
}
