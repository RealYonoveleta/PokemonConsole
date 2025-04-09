package com.yonoveleta.pokemon.event.trainer;

import com.yonoveleta.pokemon.event.AbstractEventHandler;
import com.yonoveleta.pokemon.event.EventDispatcher;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.ui.events.trainer.AskForMoveEvent;

public class TrainerEventHandler extends AbstractEventHandler<Trainer> {

	public TrainerEventHandler(Trainer entity) {
		super(entity);
	}

	@Override
	public void registerListeners() {
		onEvent(ChooseMoveEvent.class, this::handleChooseMove);
	}
	
	private void handleChooseMove(ChooseMoveEvent event) {
		EventDispatcher.dispatchTo(entity, new AskForMoveEvent(event.getParticipant(), event.getCallback()));
	}

}
