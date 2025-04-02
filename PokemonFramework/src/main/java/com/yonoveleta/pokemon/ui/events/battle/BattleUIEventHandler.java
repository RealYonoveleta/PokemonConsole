package com.yonoveleta.pokemon.ui.events.battle;

import java.util.concurrent.CompletableFuture;

import com.yonoveleta.pokemon.event.AbstractEventHandler;
import com.yonoveleta.pokemon.event.EventDispatcher;
import com.yonoveleta.pokemon.ui.BattleUI;

public class BattleUIEventHandler extends AbstractEventHandler<BattleUI> {

	public BattleUIEventHandler(BattleUI entity) {
		super(entity);
	}

	@Override
	public void registerListeners() {
		EventDispatcher.registerListener(DisplayBattleStartEvent.class, this::handleDisplayBattleStart);
		EventDispatcher.registerListener(DisplayPokemonStatesEvent.class, this::handleDisplayPokemonStates);
		EventDispatcher.registerListener(DisplayAskForMoveEvent.class, this::handleAskForMove);
	}

	private void handleDisplayBattleStart(DisplayBattleStartEvent event) {
		entity.displayBattleStart();
	}

	private void handleDisplayPokemonStates(DisplayPokemonStatesEvent event) {
		entity.showPokemonStates(event.getParticipants());
	}

	private void handleAskForMove(DisplayAskForMoveEvent event) {
		CompletableFuture.supplyAsync(() -> entity.askForMoveChoice(event.getParticipant().getCurrentPokemon()))
				.thenAccept(choice -> event.respond(choice));
	}

}
