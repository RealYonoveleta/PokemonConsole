package com.yonoveleta.pokemon.move;

import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.pokemon.Pokemon;

public class MoveUI {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();

    public void displayMoveUsed(String moveName, Pokemon source) {
        console.displayMessage("%n%s used %s!%n", source.getName(), moveName);
    }

    public void displaySuperEffective() {
        console.displayMessage("It's super effective!\n");
    }

    public void displayNotVeryEffective() {
        console.displayMessage("It's not very effective...\n");
    }

    public void displayNoPP(String moveName) {
        console.displayMessage("%s has no PP left! It can't be used.\n", moveName);
    }

    public void displayDamageDealt(Pokemon target, int damage) {
        console.displayMessage("%s took %d damage!%n", target.getName(), damage);
    }

    public void displayMoveFailed() {
        console.displayMessage("But it failed!\n");
    }
    
    public void displayAttackMessage(Pokemon user, Move move, Pokemon target, int damage) {
		console.displayMessage("%n%s used %s! %s took %d damage!%n", 
	            user.getName(), move.getName(), target.getName(), damage);
	}
    
    public void displayStatusMoveMessage(Pokemon user, Move move, Pokemon target) {
		console.displayMessage("%n%s used %s!%n", 
	            user.getName(), move.getName(), target.getName());
	}
    
    public void displayEffectivenessMessage(double multiplier) {
	    if (multiplier == 0.0) {
	        console.displayMessage("The move has no effect!");
	    } else if (multiplier >= 2.0) {
	        console.displayMessage("It's super effective!");
	    } else if (multiplier < 1.0) {
	        console.displayMessage("It's not very effective...");
	    } 
	}
}