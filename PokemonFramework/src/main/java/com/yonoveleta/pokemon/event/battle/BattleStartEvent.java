package com.yonoveleta.pokemon.event.battle;

import java.util.List;

import com.yonoveleta.pokemon.event.Event;
import com.yonoveleta.pokemon.trainer.Trainer;

public class BattleStartEvent implements Event {

	private final List<Trainer> participants;
	
	public BattleStartEvent(List<Trainer> participants) {
		this.participants = participants;
	}

	public List<Trainer> getParticipants() {
		return participants;
	}

}
