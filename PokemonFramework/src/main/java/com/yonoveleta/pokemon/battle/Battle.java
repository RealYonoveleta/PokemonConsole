package com.yonoveleta.pokemon.battle;

import java.util.concurrent.ThreadLocalRandom;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.turn.TurnManager;

public class Battle {

	private static final BattleUI battleUI = new BattleUI();
	private TurnManager turnManager = new TurnManager();

	Trainer player, rival;

	public Battle(Trainer player, Trainer rival) {
		this.player = player;
		this.rival = rival;
	}

	public void start() {
		battleUI.displayBattleStart();

		Pokemon currentPlayerPokemon;
		Pokemon currentRivalPokemon;

		while (player.getHealthyPokemonCount() > 0 && rival.getHealthyPokemonCount() > 0) {
			currentPlayerPokemon = this.player.getCurrentPokemon();
			currentRivalPokemon = this.rival.getCurrentPokemon();

			battleUI.showCurrentPokemonsHp(player, currentPlayerPokemon, rival, currentRivalPokemon);

			int option = battleUI.askForMoveChoice(currentPlayerPokemon);
			turnManager.addAction(player, currentPlayerPokemon, currentPlayerPokemon.getMove(option),
					currentRivalPokemon, rival);

			// Random ai action
			int randomMove = ThreadLocalRandom.current().nextInt(0, currentRivalPokemon.movesKnown());
			turnManager.addAction(rival, currentRivalPokemon, currentRivalPokemon.getMove(randomMove),
					currentPlayerPokemon, player);

			turnManager.processTurn();
		}

		battleUI.announceWinner(player.getHealthyPokemonCount() > 0 ? player : rival);
	}

}
