package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.di.annotation.UIManagerType;
import com.yonoveleta.pokemon.ui.PokemonUI;
import com.yonoveleta.pokemon.ui.impl.DefaultPokemonUI;

@UIManagerType
public class PokemonUIManager extends AbstractUIManager<PokemonUI> {
	
	private static PokemonUIManager instance = new PokemonUIManager();

	private PokemonUIManager() {
		setUI(new DefaultPokemonUI());
	}
	
	public static PokemonUIManager getInstance() {
		return instance;
	}
	
}
