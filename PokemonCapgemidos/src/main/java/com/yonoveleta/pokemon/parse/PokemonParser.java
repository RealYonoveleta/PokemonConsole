package com.yonoveleta.pokemon.parse;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonImpl;

public class PokemonParser extends AbstractParser<Pokemon> {
	
	private String pokemonFile;

	public PokemonParser(String pokemonFile) {
		this.pokemonFile = pokemonFile;
	}

	@Override
	protected String getFilePath() {
		return pokemonFile;
	}

	@Override
	protected Type getListType() {
		return new TypeToken<List<PokemonImpl>>() {}.getType();
	}


}
