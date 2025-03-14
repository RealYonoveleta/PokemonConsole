package battle;

import java.util.Random;
import java.util.Scanner;

import pokemon.Pokemon;
import pokemon.PokemonState;
import trainer.Trainer;

public class Battle {

	private Random rd = new Random();
	private Scanner userInput = new Scanner(System.in);
	Trainer player, rival;
	
	public Battle(Trainer player, Trainer rival) {
		this.player = player;
		this.rival = rival;
	}
	
	public void start() {
		Pokemon currentPlayerPokemon;
		Pokemon currentRivalPokemon;
		
		while(player.healthyPokemonCount() > 0 && rival.healthyPokemonCount() > 0) {
			checkActivePokemon(player);
			checkBotActivePokemon(rival);
			
			currentPlayerPokemon = this.player.getCurrentPokemon();
			currentRivalPokemon = this.rival.getCurrentPokemon();
			
			showCurrentPokemonsHp(currentPlayerPokemon, currentRivalPokemon);
			showMoves(currentPlayerPokemon);
			chooseOption(currentPlayerPokemon);
			
			currentRivalPokemon.useMove(chooseRandomMove(currentRivalPokemon), currentPlayerPokemon);
		}
		
		Trainer winner = player.healthyPokemonCount() > 0 ? player : rival;
		
		System.out.printf("\n%s won!\n", winner.getName());
	}
	
	private void checkActivePokemon(Trainer trainer) {
		Pokemon trainerCurrentPokemon = trainer.getCurrentPokemon();
		if(trainerCurrentPokemon.getState() == PokemonState.FAINTED) {
			System.out.printf("\n%s fainted...\n\n", trainerCurrentPokemon.getName());
			trainer.setActivePokemon(userInput);
		}
	}
	
	private void checkBotActivePokemon(Trainer trainer) {
		Pokemon trainerCurrentPokemon = trainer.getCurrentPokemon();
		if(trainerCurrentPokemon.getState() == PokemonState.FAINTED) {
			System.out.printf("\n%s fainted...\n\n", trainerCurrentPokemon.getName());
			chooseRandomPokemon(trainer);
		}
	}
	
	private void showCurrentPokemonsHp(Pokemon currentPlayerPokemon, Pokemon currentRivalPokemon) {
		System.out.printf("\n%s(%s): %dhp vs %s(%s): %dhp\n\n", 
				this.player.getName(),
				currentPlayerPokemon.getName(),
				currentPlayerPokemon.getHp(),
				this.rival.getName(),
				currentRivalPokemon.getName(),
				currentRivalPokemon.getHp());
	}
	
	private void showMoves(Pokemon currentPokemon) {
		System.out.printf("%s: \n", currentPokemon.getName());
		currentPokemon.showMoveset();
	}
	
	private void chooseOption(Pokemon currentPokemon) {
		System.out.printf("\nWhat should %s do?: \n", currentPokemon.getName());
		int option = userInput.nextInt() - 1;
		currentPokemon.useMove(option, rival.getCurrentPokemon());
	}
	
	public int chooseRandomMove(Pokemon pokemon) {
		return rd.nextInt(pokemon.getMoveset().size());
	}
	
	public void chooseRandomPokemon(Trainer trainer) {
		int rdPokemon;
		
		do {
			rdPokemon = rd.nextInt(trainer.getPokemons().size());
		} while(trainer.getPokemons().get(rdPokemon).getState() == PokemonState.FAINTED);
		
		trainer.setActivePokemon(rdPokemon);
	}
	
}
