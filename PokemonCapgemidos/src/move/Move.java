package move;

import pokemon.Pokemon;
import type.Type;

public interface Move {
	
	public boolean use(Pokemon source, Pokemon target);
	
	public String useMessage();
	
	public int getPPs();
	
	public Type getType();

	public String getName();
	
	public MoveType getMoveType();

}
