package move;

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

}
