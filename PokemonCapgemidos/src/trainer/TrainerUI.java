package trainer;

import io.ConsoleHandler;

public class TrainerUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	public void setActivePokemon(Trainer trainer) {
		int option = console.askForChoice("Pokemons: \n",
				trainer.getPokemons().stream().map(pokemon -> String.format("%s hp:%d\n", pokemon.getName(), pokemon.getHp()))
						.toArray(String[]::new));
		
		trainer.setActivePokemon(option);
	}

}
