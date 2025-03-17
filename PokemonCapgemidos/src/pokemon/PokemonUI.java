package pokemon;

import io.ConsoleHandler;
import move.Move;

public class PokemonUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();

	public void showMoveset(Pokemon pokemon) {
		console.displayList("", pokemon.getMoveset().stream().map(Move::getName).toArray(String[]::new));
	}
	
	public void learnMove(Pokemon pokemon, Move newMove) {
		if (pokemon.movesKnown() == 4) {
			console.displayMessage("\n%s can't learn more moves\n", pokemon.getName());
			int option = console.askForChoice(
					String.format("Do you want to forget a move to learn %s?\n", newMove.getName()), "Yes", "No");
			if (option == 0) {
				replaceMove(pokemon, newMove);
			} else {
				console.displayMessage("%s not learned\n", newMove.getName());
			}
		} else {
			console.displayMessage("%s learned %s!\n", pokemon.getName(), newMove.getName());
			((PokemonImpl)pokemon).addMove(newMove);
		}
	}
	
	public void replaceMove(Pokemon pokemon, Move newMove) {
		// Display available moves for forgetting
	    int option = console.askForChoice(
	            String.format("%s wants to learn %s, but it already knows 4 moves.\nWhat move should be forgotten?", 
	                          pokemon.getName(), newMove.getName()),
	            pokemon.getMoveset().stream().map(Move::getName).toArray(String[]::new));

	    // Move being forgotten
	    Move forgottenMove = pokemon.getMoveset().get(option);

	    // Mimic Pokémon hesitation before forgetting
	    console.displayMessage("%s is trying to forget %s...\n", pokemon.getName(), forgottenMove.getName());
	    console.displayMessage("1... 2... and... ...\n");
	    
	    // Forget the move and learn the new one
	    pokemon.forgetMove(option);
	    ((PokemonImpl)pokemon).addMove(newMove);

	    // Display move learned message
	    console.displayMessage("%s forgot how to use %s.\n", pokemon.getName(), forgottenMove.getName());
	    console.displayMessage("And it learned %s!\n", newMove.getName());
	}

}
