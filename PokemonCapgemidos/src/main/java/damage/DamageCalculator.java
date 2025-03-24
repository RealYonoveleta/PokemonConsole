package damage;

import move.Move;
import pokemon.Pokemon;

public interface DamageCalculator {
	
	int calculateDamage(Pokemon user, Move move, Pokemon target, double typeEffectiveness, double stabMultiplier);

}
