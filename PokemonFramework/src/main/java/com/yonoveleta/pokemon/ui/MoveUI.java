package com.yonoveleta.pokemon.ui;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;

public interface MoveUI {
	
	void displayMoveUsed(String moveName, Pokemon source);
	
	void displaySuperEffective();
	
	void displayNotVeryEffective();
	
	void displayNoPP(String moveName);
	
	void displayDamageDealt(Pokemon target, int damage);
	
	void displayMoveFailed();
	
	void displayAttackMessage(Pokemon user, Move move, Pokemon target, int damage);
	
	void displayStatusMoveMessage(Pokemon user, Move move, Pokemon target);
	
	void displayEffectivenessMessage(double multiplier);

}
