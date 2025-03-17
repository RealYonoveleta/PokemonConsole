package move;

import io.ConsoleHandler;
import pokemon.Pokemon;

public class moveUI {
	
	private static final ConsoleHandler console = io.ConsoleHandler.getInstance();

    public void displayMoveUsed(String moveName, Pokemon source) {
        console.displayMessage("%s used %s!\n", source.getName(), moveName);
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
        console.displayMessage("%s took %d damage!\n", target.getName(), damage);
    }

    public void displayMoveFailed() {
        console.displayMessage("But it failed!\n");
    }
}