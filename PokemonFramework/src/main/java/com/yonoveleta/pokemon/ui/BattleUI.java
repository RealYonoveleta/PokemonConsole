package com.yonoveleta.pokemon.ui;

import java.util.List;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;

public interface BattleUI {
	
	void displayBattleStart();
	
	void showPokemonStates(List<Trainer> participants);
	
	int askForMoveChoice(Pokemon pokemon);
	
	void announceFaint(Pokemon pokemon);
	
	void announceWinner(Trainer winner);

}
