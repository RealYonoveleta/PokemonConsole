package turn;

import io.ConsoleHandler;
import move.Move;
import pokemon.Pokemon;
import type.TypeChart;

public class TurnAction {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
    Pokemon user;
    Pokemon target;
    Move move;

    public TurnAction(Pokemon user, Move move, Pokemon target) {
        this.user = user;
        this.move = move;
        this.target = target;
    }
    
    public void run() {
        if (!user.hasMove(move)) {
            console.displayMessage("%s does not know %s!\n", user.getName(), move.getName());
            return;
        }

        if (move.getPPs() <= 0) {
            console.displayMessage("%s has no PP left for %s!\n", user.getName(), move.getName());
            return;
        }

        int damage = calculateDamage();
        target.takeDamage(damage);
        move.reducePPs();

        console.displayMessage("%s used %s! %s took %d damage!\n", 
            user.getName(), move.getName(), target.getName(), damage);
    }

    private int calculateDamage() {
        double stabMultiplier = user.getTypes().contains(move.getType()) ? 1.5 : 1.0;
        double typeEffectiveness = TypeChart.getEffectiveness(move.getType(), target.getTypes());

        // Standard Pokémon damage formula
        int baseDamage = (int) (((2 * user.getLevel() / 5.0 + 2) * move.getPower() * (user.getAttack() / (double) target.getDefense()) / 50 + 2) 
            * stabMultiplier * typeEffectiveness);

        return Math.max(1, baseDamage); // Ensure at least 1 damage is dealt
    }
}
