package com.yonoveleta.pokemon.ui;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;

public interface BattleUI {
	
	void displayBattleStart();
	
	void showCurrentPokemonsHp(Trainer player, Pokemon playerPokemon, Trainer rival, Pokemon rivalPokemon);
	
	int askForMoveChoice(Pokemon pokemon);
	
	void announceFaint(Pokemon pokemon);
	
	void announceWinner(Trainer winner);

}
