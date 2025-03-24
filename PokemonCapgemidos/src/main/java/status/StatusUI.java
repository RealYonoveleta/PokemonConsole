package status;

import io.ConsoleHandler;
import pokemon.Pokemon;

public class StatusUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	public void displayRemovedStatus(Pokemon pokemon, Status status) {
		console.displayMessage("%n%s is no longer %s!", pokemon.getName(), status.getName());
	}
	
}
