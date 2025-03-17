package move;

import pokemon.Pokemon;
import type.Type;

public class MoveImpl implements Move {
	
	private static final moveUI moveUI = new moveUI();
	
	private String name;
	private int power;
	private String useMessage;
	private int pps;
	private Type type;
	private MoveType moveType;
	
	public MoveImpl(String name, int power, int pps, Type type, MoveType moveType) {
		this.name = name;
		this.power = power;
		this.pps = pps;
		this.type = type;
		this.moveType = moveType;
		this.useMessage = this.name;
	}
	
	public MoveImpl(String name, int power, int pps, Type type, MoveType moveType, String useMessage) {
		this(name, power, pps, type, moveType);
		this.useMessage = useMessage;
	}
	
	@Override
    public boolean use(Pokemon source, Pokemon target) {
        if (this.pps == 0) {
            moveUI.displayNoPP(this.name);
            return false;
        }

        reducePPs();
        moveUI.displayMoveUsed(this.name, source);
        
        int damage = applyDamage(target);

        if (damage == 0) {
            moveUI.displayMoveFailed();
        } else {
            moveUI.displayDamageDealt(target, damage);
        }
        
        return true;
    }
	
	private int applyDamage(Pokemon target) {
        double multiplier = 1;
        boolean superEffective = false;
        boolean notVeryEffective = false;

        for (Type type : target.getTypes()) {
            if (this.type.getStrengths().contains(type)) {
                multiplier *= 2;
                superEffective = true;
            }
            if (this.type.getWeaknesses().contains(type)) {
                multiplier /= 2;
                notVeryEffective = true;
            }
        }

        int damage = (int) Math.round(power * multiplier);
        target.takeDamage(damage);

        if (superEffective) {
            moveUI.displaySuperEffective();
        } else if (notVeryEffective) {
            moveUI.displayNotVeryEffective();
        }

        return damage;
    }
	
	private void reducePPs() {
		this.pps--;
	}
	
	@Override
	public String toString() {
		return String.format("%-15s\tpower:%d\tPPs:%-4d\tType:%s", 
				this.name, this.power, this.pps, getType().toString());
	}

	public String getName() {
		return name;
	}

	public int getPower() {
		return power;
	}

	@Override
	public String useMessage() {
		return this.useMessage;
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

}
