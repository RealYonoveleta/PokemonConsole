package move;

import type.Type;

public class MoveImpl implements Move {

	private String name;
	private int power;
	private String useMessage;
	private int pps;
	private Type type;
	private MoveType moveType;
	private int priority;

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

	public int getPriority() {
		return priority;
	}

}
