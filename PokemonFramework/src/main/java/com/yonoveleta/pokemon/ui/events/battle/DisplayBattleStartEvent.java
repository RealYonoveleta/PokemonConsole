package com.yonoveleta.pokemon.ui.events.battle;

import com.yonoveleta.pokemon.event.Event;
import com.yonoveleta.pokemon.trainer.Trainer;

public class DisplayBattleStartEvent implements Event {
	
	private final Trainer trainer1;
	private final Trainer trainer2;
	
	public DisplayBattleStartEvent(Trainer trainer1, Trainer trainer2) {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
	}

	public Trainer getTrainer1() {
		return trainer1;
	}

	public Trainer getTrainer2() {
		return trainer2;
	}

}
