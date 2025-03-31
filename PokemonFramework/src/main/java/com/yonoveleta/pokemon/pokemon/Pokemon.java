package com.yonoveleta.pokemon.pokemon;

import java.util.List;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.stat.StatType;
import com.yonoveleta.pokemon.status.Status;
import com.yonoveleta.pokemon.type.Type;

public interface Pokemon {
	
	public void takeDamage(int damage);
	
	public List<Move> getMoveset();

	public String getName();
	
	public int getHp();
	
	public PokemonState getState();
	
	public List<Type> getTypes();
	
	public void learnMove(Move move);
	
	public void forgetMove(int move);
	
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

	public void addStatus(Status status);
	
	public void removeStatus(Status status);
	
	public boolean hasStatus(Class<? extends Status> statusType);

	public void heal(int i);

	public double getMaxHP();
	
	public void modifyStat(StatType statType, int amount);
	
	public int getModifiedStat(StatType statType);
	
	public void resetAllStats();

	void replaceMove(Move newMove);
	
	boolean canMove();
	
	void setCanMove(boolean canMove);

	public List<Status> getStatuses();

	public boolean isFainted();
	
	public boolean knowsMove(Move move);
	
}
