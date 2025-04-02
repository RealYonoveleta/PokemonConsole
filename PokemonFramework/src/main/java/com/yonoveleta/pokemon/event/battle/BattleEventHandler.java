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
		EventDispatcher.registerListener(BattleStartEvent.class, this::handleBattleStarted);
	}
	
	private void handleBattleStarted(BattleStartEvent event) {
		EventDispatcher.dispatch(new DisplayBattleStartEvent(event.getTrainer1(), event.getTrainer2()));
	}

}
