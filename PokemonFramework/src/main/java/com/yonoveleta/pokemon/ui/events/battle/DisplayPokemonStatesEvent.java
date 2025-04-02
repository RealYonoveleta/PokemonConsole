package com.yonoveleta.pokemon.ui.events.battle;

import java.util.List;

import com.yonoveleta.pokemon.event.Event;
import com.yonoveleta.pokemon.trainer.Trainer;

public class DisplayPokemonStatesEvent implements Event {
	
	private List<Trainer> participants;
	
	public DisplayPokemonStatesEvent(List<Trainer> participants) {
		this.participants = participants;
	}

	public List<Trainer> getParticipants() {
		return participants;
	}
	
}
