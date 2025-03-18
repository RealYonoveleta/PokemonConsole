package pokemon;

import java.util.List;

import move.Move;
import type.Type;

public interface Pokemon {
	
	public void takeDamage(int damage);
	
	public List<Move> getMoveset();

	public String getName();
	
	public int getHp();
	
	public PokemonState getState();
	
	public List<Type> getTypes();
	
	public void learnMove(Move move);
	
	public void forgetMove(int move);
	
	public void replaceMove(Move newMove);
	
	public int getAttack();
	
	public int getDefense();
	
	public int getSpecialAttack();
	
	public int getSpecialDefense();
	
	public int getSpeed();
	
	public int getLevel();
	
	public int movesKnown();
	
	public void levelUp();
	
	public void levelUp(int levels);

	public void setState(PokemonState state);

	public boolean hasMove(Move move);
	
	public Move getMove(int move);
	
}
