package move;

import pokemon.Pokemon;

public interface Move {
	
	public void use(Pokemon source, Pokemon target);
	
	public String useMessage();

}
