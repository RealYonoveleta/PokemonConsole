package com.yonoveleta.pokemon.event.battle;

import com.yonoveleta.pokemon.battle.Battle;
import com.yonoveleta.pokemon.event.AbstractEventHandler;
import com.yonoveleta.pokemon.event.EventDispatcher;
import com.yonoveleta.pokemon.ui.events.battle.DisplayBattleStartEvent;

public class BattleEventHandler extends AbstractEventHandler<Battle> {

	public BattleEventHandler(Battle entity) {
		super(entity);
	}

	@Override
	public void registerListeners() {
		onEvent(BattleStartEvent.class, this::handleBattleStarted);
	}
	
	private void handleBattleStarted(BattleStartEvent event) {
		EventDispatcher.dispatchTo(entity, new DisplayBattleStartEvent(event.getParticipants()));
	}

}
