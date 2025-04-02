package com.yonoveleta.pokemon.ui.events.battle;

import com.yonoveleta.pokemon.event.Event;
import com.yonoveleta.pokemon.trainer.Trainer;

public class DisplayBattleWinnerEvent implements Event {

	private final Trainer winner;
	
	public DisplayBattleWinnerEvent(Trainer winner) {
		this.winner = winner;
	}

	public Trainer getWinner() {
		return winner;
	}
	
}
