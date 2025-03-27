package com.yonoveleta.pokemon.damage;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;

public interface DamageCalculator {
	
	int calculateDamage(Pokemon user, Move move, Pokemon target, double typeEffectiveness, double stabMultiplier);

}
