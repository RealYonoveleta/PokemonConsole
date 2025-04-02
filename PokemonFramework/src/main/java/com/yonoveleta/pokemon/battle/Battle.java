package com.yonoveleta.pokemon.battle;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import com.yonoveleta.pokemon.event.EventDispatcher;
import com.yonoveleta.pokemon.event.battle.BattleEventHandler;
import com.yonoveleta.pokemon.event.battle.BattleStartEvent;
import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.turn.TurnManager;
import com.yonoveleta.pokemon.ui.events.battle.AskForMoveEvent;
import com.yonoveleta.pokemon.ui.events.battle.DisplayBattleWinnerEvent;
import com.yonoveleta.pokemon.ui.events.battle.DisplayPokemonStatesEvent;

public class Battle {

	private TurnManager turnManager = new TurnManager();

	List<Trainer> participants;
	private CyclicBarrier barrier;

	{
		new BattleEventHandler(this);
	}

	public Battle(Trainer... participants) {
		this.participants = List.of(participants);
		this.barrier = new CyclicBarrier(2, () -> turnManager.processTurn());
	}

	public Trainer start() {
		EventDispatcher.dispatch(new BattleStartEvent(participants));

		while (hasActiveParticipants()) {
			// Show HP before making a move
			EventDispatcher.dispatch(new DisplayPokemonStatesEvent(participants));

			for (Trainer participant : participants) {
				EventDispatcher.dispatch(new AskForMoveEvent(participant, move -> {
					Move chosenMove = participant.getCurrentPokemon().getMove(move);
					// Process the move chosen by the participant
					turnManager.addAction(participant, participant.getCurrentPokemon(), chosenMove,
							participant.getCurrentPokemon(), participant);

					// Notify the barrier that the participant has finished their action
					try {
						barrier.await(); // Wait for other participants to finish
					} catch (Exception e) {
						e.printStackTrace();
					}
				}));
			}
		}

		Trainer winner = determineWinner();
		EventDispatcher.dispatch(new DisplayBattleWinnerEvent(winner));
		return winner;
	}

	private boolean hasActiveParticipants() {
		return participants.stream().anyMatch(p -> p.hasHealthyPokemons());
	}
	
	private Trainer determineWinner() {
	    return participants.stream()
	        .filter(participant -> participant.getHealthyPokemonCount() > 0) // Filter participants with healthy Pokémon
	        .findFirst() // If only one remains, return that participant
	        .orElse(null); // If no participants with healthy Pokémon remain, return null
	}

}
