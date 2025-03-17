package battle;

import io.ConsoleHandler;
import pokemon.Pokemon;
import trainer.Trainer;

public class BattleUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();

    public void displayBattleStart() {
        console.displayMessage("\nThe battle begins!\n");
    }

    public void showCurrentPokemonsHp(Trainer player, Pokemon playerPokemon, Trainer rival, Pokemon rivalPokemon) {
        console.displayMessage("\n%s(%s): %dhp vs %s(%s): %dhp\n\n", 
                player.getName(), playerPokemon.getName(), playerPokemon.getHp(),
                rival.getName(), rivalPokemon.getName(), rivalPokemon.getHp());
    }

    public int askForMoveChoice(Pokemon pokemon) {
        return console.askForChoiceArray(
                String.format("\nWhat should %s do?:", pokemon.getName()),
                pokemon.getMoveset().stream().map(move -> move.getName()).toArray(String[]::new)
        );
    }

    public void announceFaint(Pokemon pokemon) {
        console.displayMessage("\n%s fainted...\n\n", pokemon.getName());
    }

    public void announceWinner(Trainer winner) {
        console.displayMessage("\n%s won!\n", winner.getName());
    }
	
}
