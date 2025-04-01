package com.yonoveleta.pokemon.move;

import java.util.List;

import com.yonoveleta.pokemon.damage.DamageCalculator;
import com.yonoveleta.pokemon.effect.Effect;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.type.Type;
import com.yonoveleta.pokemon.ui.MoveUI;

public interface Move {
	
	public int getPPs();
	
	public Type getType();

	public String getName();
	
	public MoveType getMoveType();

	public int getPower();
	
	public int getPriority();
	
	public void reducePPs();

	public List<Effect> getEffects();
	
	public void execute(Pokemon user, Pokemon target);
	
	public void execute(Pokemon user, Pokemon target, DamageCalculator damageCalculator);
	
	public void addEffect(Effect effect);
	
	void setMoveUI(MoveUI moveUI);

}
