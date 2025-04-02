package com.yonoveleta.pokemon.battle;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.yonoveleta.pokemon.event.EventDispatcher;
import com.yonoveleta.pokemon.event.battle.AskForMoveEvent;
import com.yonoveleta.pokemon.event.battle.BattleEventHandler;
import com.yonoveleta.pokemon.event.battle.BattleStartEvent;
import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.turn.TurnManager;
import com.yonoveleta.pokemon.ui.events.battle.DisplayBattleWinnerEvent;
import com.yonoveleta.pokemon.ui.events.battle.DisplayPokemonStatesEvent;

public class SingleBattle implements Battle {

	private TurnManager turnManager = new TurnManager();

	Trainer participant1, participant2;

	{
		new BattleEventHandler(this);
	}

	public SingleBattle(Trainer participant1, Trainer participant2) {
		this.participant1 = participant1;
		this.participant2 = participant2;
	}

	public List<Trainer> start() {
		List<Trainer> participants = List.of(participant1, participant2);
		EventDispatcher.dispatch(new BattleStartEvent(participants));

		CountDownLatch latch = new CountDownLatch(participants.size());

		while (hasActiveParticipants()) {
			// Show HP before making a move
			EventDispatcher.dispatch(new DisplayPokemonStatesEvent(participants));

			for (Trainer participant : participants) {
				EventDispatcher.dispatch(new AskForMoveEvent(participant, move -> {
					Move chosenMove = participant.getCurrentPokemon().getMove(move);
					// Process the move chosen by the participant
					turnManager.addAction(participant, participant.getCurrentPokemon(), chosenMove,
							participant.getCurrentPokemon(), participant);

					latch.countDown(); // Decrease count when a move is chosen
				}));
			}

			try {
				latch.await(); // Block execution until all moves are received
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Process turn after all participants have chosen their move
			turnManager.processTurn();
		}

		Trainer winner = determineWinner();
		EventDispatcher.dispatch(new DisplayBattleWinnerEvent(winner));
		return List.of(winner);
	}

	private boolean hasActiveParticipants() {
		return participant1.hasHealthyPokemons() || participant2.hasHealthyPokemons();
	}

	private Trainer determineWinner() {
		return participant1.hasHealthyPokemons() ? participant1 : participant2;
	}

}
