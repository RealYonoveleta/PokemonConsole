package move;

import pokemon.Pokemon;

public class MoveImpl implements Move {
	
	private String name;
	private int power;
	private String useMessage;
	
	public MoveImpl(String name, int power) {
		this.name = name;
		this.power = power;
		this.useMessage = this.name;
	}
	
	public MoveImpl(String name, int power, String useMessage) {
		this(name, power);
		this.useMessage = useMessage;
	}
	
	@Override
	public void use(Pokemon source, Pokemon target) {
		target.takeDamage(this.power);
		System.out.printf("%s %s %s\n", source.getName(), this.useMessage, target.getName());
	}
	
	@Override
	public String toString() {
		return String.format("%s %d\n", this.name, this.power);
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

}
