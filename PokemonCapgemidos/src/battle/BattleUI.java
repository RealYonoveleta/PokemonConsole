package battle;

import io.ConsoleHandler;
import move.Move;
import pokemon.Pokemon;
import trainer.Trainer;

public class BattleUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();

	public void displayBattleStart() {
		console.displayMessage("\nThe battle begins!");
	}

	public void showCurrentPokemonsHp(Trainer player, Pokemon playerPokemon, Trainer rival, Pokemon rivalPokemon) {
		console.displayMessage("%n%s(%s Lv%d): %dhp vs %s(%s Lv%d): %dhp%n", player.getName(),
				playerPokemon.getName(), playerPokemon.getLevel(), playerPokemon.getHp(), rival.getName(),
				rivalPokemon.getName(), rivalPokemon.getLevel(), rivalPokemon.getHp());
	}

	public int askForMoveChoice(Pokemon pokemon) {
		StringBuilder prompt = new StringBuilder();
		prompt.append(String.format("\nWhat should %s do?:%n%n", pokemon.getName()));
		prompt.append(String.format("%-3s %-15s %-10s %-6s %-10s%n", "#", "Move", "Power", "PPs", "Type"));
		prompt.append("--------------------------------------------");

		return console.askForChoiceArray(prompt.toString(),
				pokemon.getMoveset().stream().map(Move::toString).toArray(String[]::new));
	}

	public void announceFaint(Pokemon pokemon) {
		console.displayMessage("\n%s fainted...\n\n", pokemon.getName());
	}

	public void announceWinner(Trainer winner) {
		console.displayMessage("\n%s won!\n", winner.getName());
	}

}
