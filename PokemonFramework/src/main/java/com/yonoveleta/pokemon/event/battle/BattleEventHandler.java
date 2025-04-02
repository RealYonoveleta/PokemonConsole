package com.yonoveleta.pokemon.event.battle;

import com.yonoveleta.pokemon.battle.SingleBattle;
import com.yonoveleta.pokemon.event.AbstractEventHandler;
import com.yonoveleta.pokemon.event.EventDispatcher;
import com.yonoveleta.pokemon.ui.events.battle.DisplayAskForMoveEvent;
import com.yonoveleta.pokemon.ui.events.battle.DisplayBattleStartEvent;

public class BattleEventHandler extends AbstractEventHandler<SingleBattle> {

	public BattleEventHandler(SingleBattle entity) {
		super(entity);
	}

	@Override
	public void registerListeners() {
		EventDispatcher.registerListener(BattleStartEvent.class, this::handleBattleStarted);
		EventDispatcher.registerListener(AskForMoveEvent.class, this::handleAskForMove);
	}
	
	private void handleBattleStarted(BattleStartEvent event) {
		EventDispatcher.dispatch(new DisplayBattleStartEvent(event.getParticipants()));
	}
	
	private void handleAskForMove(AskForMoveEvent event) {
		EventDispatcher.dispatch(new DisplayAskForMoveEvent(event.getParticipant(), event.getCallback()));
	}

}
