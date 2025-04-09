package com.yonoveleta.pokemon.ui;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;

public interface TrainerUI {
	
	void setActivePokemon(Trainer trainer);
	
	int askForMoveChoice(Pokemon pokemon);

}
