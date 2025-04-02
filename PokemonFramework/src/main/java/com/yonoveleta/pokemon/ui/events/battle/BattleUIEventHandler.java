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
		EventDispatcher.registerListener(DisplayCurrentPokemonsHP.class, this::handleDisplayCurrentPokemonsHP);
		EventDispatcher.registerListener(AskForMoveEvent.class, this::handleAskForMove);
	}

	private void handleDisplayBattleStart(DisplayBattleStartEvent event) {
		entity.displayBattleStart();
	}

	private void handleDisplayCurrentPokemonsHP(DisplayCurrentPokemonsHP event) {
		entity.showCurrentPokemonsHp(event.getTrainer1(), event.getPokemon1(), event.getTrainer2(),
				event.getPokemon2());
	}

	private void handleAskForMove(AskForMoveEvent event) {
		CompletableFuture.supplyAsync(() -> entity.askForMoveChoice(event.getParticipant().getCurrentPokemon()))
				.thenAccept(choice -> event.respond(choice));
	}

}
