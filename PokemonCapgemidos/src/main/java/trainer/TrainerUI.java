package trainer;

import io.ConsoleHandler;

public class TrainerUI {

	private static final ConsoleHandler console = ConsoleHandler.getInstance();

	public void setActivePokemon(PlayerTrainer trainer) {
		int option = console.askForChoice("Pokemons: ",
				trainer.getPokemons().stream().map(pokemon -> String.format("%-15s hp:%-6d", pokemon.getName(), pokemon.getHp()))
						.toArray(String[]::new));
		
		trainer.setActivePokemon(option);
	}

}
