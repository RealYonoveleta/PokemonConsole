package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.ui.PokemonUI;
import com.yonoveleta.pokemon.ui.impl.DefaultPokemonUI;

public class PokemonUIManager implements PokemonUI {
	
    private static PokemonUIManager instance = new PokemonUIManager();

    private PokemonUI pokemonUI;  // The UI being used for all Pokemon

    private PokemonUIManager() {
        this.pokemonUI = new DefaultPokemonUI();
    }

    public static synchronized PokemonUIManager getInstance() {
        return instance;
    }

    public void setPokemonUI(PokemonUI newUI) {
        this.pokemonUI = newUI;
    }

    public PokemonUI getPokemonUI() {
        return this.pokemonUI;
    }

	@Override
	public void showMoveset(Pokemon pokemon) {
		pokemonUI.showMoveset(pokemon);
	}

	@Override
	public void showLevelUpMessage(Pokemon pokemon) {
		pokemonUI.showLevelUpMessage(pokemon);
	}

	@Override
	public void showNoPpsLeftMessage(Pokemon pokemon, Move move) {
		pokemonUI.showNoPpsLeftMessage(pokemon, move);
	}

	@Override
	public void showMoveNotLearntMessage(Pokemon pokemon, Move move) {
		pokemonUI.showMoveNotLearntMessage(pokemon, move);
	}

	@Override
	public void showFaintedMessage(Pokemon pokemon) {
		pokemonUI.showFaintedMessage(pokemon);
	}

	@Override
	public void showCantLearnMoreMovesMessage(Pokemon pokemon, Move newMove) {
		pokemonUI.showCantLearnMoreMovesMessage(pokemon, newMove);
	}

	@Override
	public int askToForgetMove(Move newMove) {
		return pokemonUI.askToForgetMove(newMove);
	}

	@Override
	public void showMoveLearnedMessage(Pokemon pokemon, Move newMove) {
		pokemonUI.showMoveLearnedMessage(pokemon, newMove);
	}

	@Override
	public void showMoveNotLearned(Move newMove) {
		pokemonUI.showMoveNotLearned(newMove);
	}

	@Override
	public int askForMoveToForget(Pokemon pokemon) {
		return askForMoveToForget(pokemon);
	}

	@Override
	public void showMoveReplacedMessage(Pokemon pokemon, Move oldMove, Move newMove) {
		pokemonUI.showMoveReplacedMessage(pokemon, oldMove, newMove);
	}
	
}
