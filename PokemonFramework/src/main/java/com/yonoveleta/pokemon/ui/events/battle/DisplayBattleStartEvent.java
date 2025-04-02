package com.yonoveleta.pokemon.ui.events.battle;

import java.util.List;

import com.yonoveleta.pokemon.event.Event;
import com.yonoveleta.pokemon.trainer.Trainer;

public class DisplayBattleStartEvent implements Event {
	
	private final List<Trainer> participants;
	
	public DisplayBattleStartEvent(List<Trainer> participants) {
		this.participants = participants;
	}

	public List<Trainer> getParticipants() {
		return participants;
	}

}
