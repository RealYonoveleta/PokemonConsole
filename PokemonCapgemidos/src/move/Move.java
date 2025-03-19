package move;

import java.util.List;

import damage.DamageCalculator;
import effect.Effect;
import pokemon.Pokemon;
import type.Type;

public interface Move {
	
	public String useMessage();
	
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

}
