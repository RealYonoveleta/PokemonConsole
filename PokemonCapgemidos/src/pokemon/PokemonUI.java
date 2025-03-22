package pokemon;

import java.util.List;

import io.ConsoleHandler;
import move.Move;

public class PokemonUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();

	public void showMoveset(Pokemon pokemon) {
		console.displayList("", pokemon.getMoveset().stream().map(Move::getName).toArray(String[]::new));
	}

	public void showMoveset(List<Move> moves) {
		console.displayMessage("%-3s %-15s %-10s %-6s %-10s%n", "#", "Move", "Power", "PPs", "Type"); // Header
		console.displayMessage("--------------------------------------------");

		int index = 1;
		for (Move move : moves) {
			console.displayMessage("%-3d %s", index++, move);
		}
	}

	public void showLevelUpMessage(Pokemon pokemon) {
		console.displayMessage("%nCongratulations! Your %s grew to level %d!%n", pokemon.getName(), pokemon.getLevel());
	}

	public void showNoPpsLeftMessage(Pokemon pokemon, Move move) {
		console.displayMessage("%s has no PP left for %s!\n", pokemon.getName(), move.getName());
	}

	public void showMoveNotLearntMessage(Pokemon pokemon, Move move) {
		console.displayMessage("%s does not know %s!\n", pokemon.getName(), move.getName());
	}

	public void showFaintedMessage(Pokemon pokemon) {
		console.displayMessage("%n%s has fainted!%n", pokemon.getName());
	}

	public void showCantLearnMoreMovesMessage(Pokemon pokemon, Move newMove) {
		console.displayMessage("\n%s is trying to learn %s but can't learn more moves\n", pokemon.getName(),
				newMove.getName());
	}

	public int askToForgetMove(Move newMove) {
		return console.askForChoice(String.format("Do you want to forget a move to learn %s?\n", newMove.getName()),
				"Yes", "No");
	}

	public void showMoveLearnedMessage(Pokemon pokemon, Move newMove) {
		console.displayMessage("%s learned %s!\n", pokemon.getName(), newMove.getName());
	}

	public void showMoveNotLearned(Move newMove) {
		console.displayMessage("%s not learned\n.", newMove.getName());
	}

	public int askForMoveToForget(Pokemon pokemon) {
		return console.askForChoiceArray("Select a move: ",
				pokemon.getMoveset().stream().map(Move::getName).toArray(String[]::new));
	}

	public void showMoveReplacedMessage(Pokemon pokemon, Move oldMove, Move newMove) {
		console.displayMessage("%s forgot %s and learned %s!\n", pokemon.getName(), oldMove.getName(),
				newMove.getName());
	}

}
