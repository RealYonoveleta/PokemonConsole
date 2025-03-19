package turn;

import java.util.PriorityQueue;
import java.util.Queue;

import io.ConsoleHandler;
import move.Move;
import pokemon.Pokemon;
import pokemon.PokemonState;
import pokemon.PokemonUI;
import status.Status;
import trainer.Trainer;

public class TurnManager {
	
	private static final PokemonUI pokemonUI = new PokemonUI();
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
    private Queue<TurnAction> actionQueue;

    public TurnManager() {
        this.actionQueue = new PriorityQueue<>(new TurnComparator());
    }

    public void addAction(Trainer trainer, Pokemon user, Move move, Pokemon target, Trainer rival) {
        actionQueue.add(new TurnAction(trainer, user, move, target, rival));
    }

    public void processTurn() {
        while (!actionQueue.isEmpty()) {
            TurnAction action = actionQueue.poll(); // Get the highest-priority action

            if (action.user.getHp() > 0) { // Ensure Pokémon is still active
                if (!canExecuteMove(action)) {
                    continue; // Skip if flinched or paralyzed
                }
                action.run();
            } 
            
            checkForFaintedPokemon(action.getRival(), action.getTarget());
        }
        applyEndOfTurnEffects(); // Handle weather, poison, burn, etc.
    }

    private boolean canExecuteMove(TurnAction action) {
        if (action.user.hasStatus(Status.FLINCHED)) {
            console.displayMessage("%s flinched and couldn't move!", action.user.getName());
            action.user.setState(PokemonState.NORMAL);
            return false;
        }
        if (action.user.hasStatus(Status.PARALYZED) && Math.random() < 0.25) {
            console.displayMessage("%s is paralyzed and couldn't move!", action.user.getName());
            return false;
        }
        return true;
    }
    
    private void checkForFaintedPokemon(Trainer trainer, Pokemon pokemon) {
        if (pokemon.getState() == PokemonState.FAINTED) {
            // Trigger fainted Pokémon logic
            pokemonUI.showFaintedMessage(pokemon);
            trainer.setActivePokemon();
        }
    }

    private void applyEndOfTurnEffects() {
        
    }
    
}

