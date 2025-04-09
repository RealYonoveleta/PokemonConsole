package com.yonoveleta.pokemon.ui.events.trainer;

import com.yonoveleta.pokemon.event.AbstractEventHandler;
import com.yonoveleta.pokemon.ui.TrainerUI;

public class TrainerUIEventHandler extends AbstractEventHandler<TrainerUI> {

	public TrainerUIEventHandler(TrainerUI entity) {
		super(entity);
	}

	@Override
	public void registerListeners() {
		onEvent(AskForMoveEvent.class, this::handleAskForMove);
	}
	
	private void handleAskForMove(AskForMoveEvent event) {
		int choice = entity.askForMoveChoice(event.getParticipant().getCurrentPokemon());
		event.respond(choice);
	}

}
