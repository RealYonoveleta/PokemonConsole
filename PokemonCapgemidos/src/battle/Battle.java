package battle;

import pokemon.Pokemon;
import pokemon.PokemonState;
import trainer.Trainer;

public class Battle {

	private static final BattleUI battleUI = new BattleUI();

	Trainer player, rival;

	public Battle(Trainer player, Trainer rival) {
		this.player = player;
		this.rival = rival;
	}

	public void start() {
        battleUI.displayBattleStart();

        Pokemon currentPlayerPokemon;
        Pokemon currentRivalPokemon;

        while (player.healthyPokemonCount() > 0 && rival.healthyPokemonCount() > 0) {
            checkActivePokemon(player);
            checkActivePokemon(rival);

            currentPlayerPokemon = this.player.getCurrentPokemon();
            currentRivalPokemon = this.rival.getCurrentPokemon();

            battleUI.showCurrentPokemonsHp(player, currentPlayerPokemon, rival, currentRivalPokemon);

            int option = battleUI.askForMoveChoice(currentPlayerPokemon);
            currentPlayerPokemon.useMove(option, currentRivalPokemon);

            rival.chooseRandomMove(currentPlayerPokemon);
        }

        battleUI.announceWinner(player.healthyPokemonCount() > 0 ? player : rival);
    }

	private void checkActivePokemon(Trainer trainer) {
        Pokemon trainerCurrentPokemon = trainer.getCurrentPokemon();
        if (trainerCurrentPokemon.getState() == PokemonState.FAINTED) {
            battleUI.announceFaint(trainerCurrentPokemon);
            if (trainer.equals(player)) {
                trainer.setActivePokemon();
            } else {
                trainer.chooseRandomPokemon();
            }
        }
    }

}
