package com.yonoveleta.pokemon.damage;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;

public class Gen1Calculator implements DamageCalculator {

	@Override
	public int calculateDamage(Pokemon user, Move move, Pokemon target, double typeEffectiveness,
			double stabMultiplier) {
		int baseDamage = (int) (((2 * user.getLevel() / 5.0 + 2) * move.getPower()
				* (user.getAttack() / (double) target.getDefense()) / 50 + 2) * stabMultiplier * typeEffectiveness);

		return Math.max(1, baseDamage); // Ensure at least 1 damage is dealt
	}

}
