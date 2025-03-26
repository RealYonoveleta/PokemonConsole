package com.yonoveleta.pokemon.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonState;
import com.yonoveleta.pokemon.pokemon.PokemonUI;
import com.yonoveleta.pokemon.status.Status;
import com.yonoveleta.pokemon.trainer.Trainer;

public class TurnManager {

	private static final PokemonUI pokemonUI = new PokemonUI();

	private Queue<TurnAction> actionQueue;
	private List<TurnAction> actionsPerformed;

	public TurnManager() {
		this.actionQueue = new PriorityQueue<>(new TurnComparator());
		this.actionsPerformed = new ArrayList<>();
	}

	public void addAction(Trainer trainer, Pokemon user, Move move, Pokemon target, Trainer rival) {
		actionQueue.add(new TurnAction(trainer, user, move, target, rival));
	}

	public void processTurn() {
		// Start of turn: apply START_OF_TURN statuses
		for (TurnAction action : actionQueue) {
			applyStartOfTurnStatuses(action.getUser());
		}

		while (!actionQueue.isEmpty()) {
			TurnAction action = actionQueue.poll(); // Get the highest-priority action
			actionsPerformed.add(action);

			if (action.getUser().getHp() > 0) { // Ensure Pokémon is still active
				// During turn: apply DURING_TURN statuses
				applyDuringTurnStatuses(action.getUser());

				if (!canExecuteMove(action)) {
					continue;
				}
				action.run();
			}

			checkForFaintedPokemon(action.getRival(), action.getTarget());
		}

		// End of turn: apply END_OF_TURN statuses
		for (TurnAction action : actionsPerformed) {
			applyEndOfTurnStatuses(action.getUser());
			checkForFaintedPokemon(action.getTrainer(), action.getUser());
		}

		// Clear actions for the next turn
		actionsPerformed.clear();
	}

	private boolean canExecuteMove(TurnAction action) {
		return action.getUser().canMove();
	}

	// Method for applying START_OF_TURN statuses
	private void applyStartOfTurnStatuses(Pokemon pokemon) {
	    for (int i = pokemon.getStatuses().size() - 1; i >= 0; i--) {
	        Status status = pokemon.getStatuses().get(i);
	        status.onStartOfTurn(pokemon);
	    }
	}

	// Method for applying DURING_TURN statuses
	private void applyDuringTurnStatuses(Pokemon pokemon) {
	    for (int i = pokemon.getStatuses().size() - 1; i >= 0; i--) {
	        Status status = pokemon.getStatuses().get(i);
	        status.onDuringTurn(pokemon);
	    }
	}

	// Method for applying END_OF_TURN statuses
	private void applyEndOfTurnStatuses(Pokemon pokemon) {
	    for (int i = pokemon.getStatuses().size() - 1; i >= 0; i--) {
	        Status status = pokemon.getStatuses().get(i);
	        status.onEndOfTurn(pokemon);
	    }
	}

	private void checkForFaintedPokemon(Trainer trainer, Pokemon pokemon) {
		if (pokemon.getState() == PokemonState.FAINTED) {
			// Trigger fainted Pokémon logic
			pokemonUI.showFaintedMessage(pokemon);
			trainer.setActivePokemon();
		}
	}

}
