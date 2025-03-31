package com.yonoveleta.pokemon.ui;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;

public interface PokemonUI {
	
	void showMoveset(Pokemon pokemon);
	
	void showLevelUpMessage(Pokemon pokemon);
	
	void showNoPpsLeftMessage(Pokemon pokemon, Move move);
	
	void showMoveNotLearntMessage(Pokemon pokemon, Move move);
	
	void showFaintedMessage(Pokemon pokemon);
	
	void showCantLearnMoreMovesMessage(Pokemon pokemon, Move newMove);
	
	int askToForgetMove(Move newMove);
	
	void showMoveLearnedMessage(Pokemon pokemon, Move newMove);
	
	void showMoveNotLearned(Move newMove);
	
	int askForMoveToForget(Pokemon pokemon);
	
	void showMoveReplacedMessage(Pokemon pokemon, Move oldMove, Move newMove);

}
