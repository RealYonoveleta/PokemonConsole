package move;

import java.util.ArrayList;
import java.util.List;

import damage.DamageCalculator;
import damage.Gen1Calculator;
import effect.Effect;
import pokemon.Pokemon;
import type.Type;
import type.TypeChart;

public class MoveImpl implements Move {
	
	private static final MoveUI moveUI = new MoveUI();

	private String name;
	private int power;
	private int pps;
	private Type type;
	private MoveType moveType;
	private int priority;
	
	List<Effect> effects = new ArrayList<>();

	public MoveImpl(String name, int power, int pps, Type type, MoveType moveType) {
		this.name = name;
		this.power = power;
		this.pps = pps;
		this.type = type;
		this.moveType = moveType;
	}
	
	public MoveImpl(String name, int power, int pps, Type type, MoveType moveType, List<Effect> effects) {
		this(name, power, pps, type, moveType);
		this.effects = effects;
	}
 
	public MoveImpl(Move other) {
		if (other == null) {
	        System.err.println("Warning: Attempted to create MoveImpl from a null Move.");
	        return;
	    }
		
		this.name = other.getName();
	    this.power = other.getPower();
	    this.pps = other.getPPs();
	    this.type = other.getType();
	    this.moveType = other.getMoveType();
	}

	public void reducePPs() {
		this.pps--;
	}

	@Override
	public String toString() {
		return String.format("%-15s %-10d %-6d %-10s", name, power, pps, type);
	}

	public String getName() {
		return name;
	}

	public int getPower() {
		return power;
	}

	@Override
	public int getPPs() {
		return this.pps;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	@Override
	public MoveType getMoveType() {
		return this.moveType;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public List<Effect> getEffects() {
		return new ArrayList<>(this.effects);
	}
	
	@Override
	public void addEffect(Effect effect) {
		this.effects.add(effect);
	}
	
	private void dealDamage(Pokemon user, Pokemon target, DamageCalculator damageCalculator) {
		double typeEffectiveness = TypeChart.getEffectiveness(this.type, target.getTypes());
		double stabMultiplier = user.getTypes().contains(this.type) ? 1.5 : 1.0;
	    int damage = damageCalculator.calculateDamage(user, this, target, typeEffectiveness, stabMultiplier);
	    target.takeDamage(damage);
	    moveUI.displayAttackMessage(user, this, target, damage);
	    moveUI.displayEffectivenessMessage(typeEffectiveness);
	}

	@Override
	public void execute(Pokemon user, Pokemon target) {
		execute(user, target, new Gen1Calculator());
	}
	
	@Override
	public void execute(Pokemon user, Pokemon target, DamageCalculator damageCalculator) {
		if (this.power > 0) { // Only deal damage if the move has power
	        dealDamage(user, target, damageCalculator);
	    }
		
		for (Effect effect : effects) {
	        effect.apply(user, target);
	    }
	    
	    if(getMoveType() == MoveType.STATUS) {
	    	moveUI.displayStatusMoveMessage(user, this, target);
	    }
	    
	    reducePPs();
	}

}
