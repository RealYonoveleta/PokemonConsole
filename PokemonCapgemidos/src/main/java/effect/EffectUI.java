package effect;

import io.ConsoleHandler;
import pokemon.Pokemon;
import status.Status;

public class EffectUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	public void displayEffectAppliedMessage(Pokemon pokemon, Status status) {
		console.displayMessage(pokemon.getName() + " was inflicted with " + status);
	}

}
