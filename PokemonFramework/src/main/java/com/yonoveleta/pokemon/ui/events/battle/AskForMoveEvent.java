package com.yonoveleta.pokemon.ui.events.battle;

import java.util.function.Consumer;

import com.yonoveleta.pokemon.event.Event;
import com.yonoveleta.pokemon.trainer.Trainer;

public class AskForMoveEvent implements Event {
	
	private final Trainer participant;
	private final Consumer<Integer> callback;
	
	public AskForMoveEvent(Trainer participant, Consumer<Integer> callback) {
		this.participant = participant;
		this.callback = callback;
	}
	
	public Trainer getParticipant() {
		return participant;
	}

	public void respond(int choice) {
		callback.accept(choice);
	}

}
