package com.yonoveleta.pokemon.ui.events.battle;

import com.yonoveleta.pokemon.event.AbstractEventHandler;
import com.yonoveleta.pokemon.ui.BattleUI;

public class BattleUIEventHandler extends AbstractEventHandler<BattleUI> {

	public BattleUIEventHandler(BattleUI entity) {
		super(entity);
	}

	@Override
	public void registerListeners() {
		onEvent(DisplayBattleStartEvent.class, this::handleDisplayBattleStart);
		onEvent(DisplayPokemonStatesEvent.class, this::handleDisplayPokemonStates);
		
	}

	private void handleDisplayBattleStart(DisplayBattleStartEvent event) {
		entity.displayBattleStart();
	}

	private void handleDisplayPokemonStates(DisplayPokemonStatesEvent event) {
		entity.showPokemonStates(event.getParticipants());
	}

}
