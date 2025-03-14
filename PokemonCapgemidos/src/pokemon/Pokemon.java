package pokemon;

import java.util.List;

import move.Move;

public interface Pokemon {
	
	public void showMoveset();
	
	public void useMove(int move, Pokemon target);
	
	public void takeDamage(int damage);
	
	public List<Move> getMoveset();

	public String getName();
	
	public int getHp();
	
	public PokemonState getState();

}
