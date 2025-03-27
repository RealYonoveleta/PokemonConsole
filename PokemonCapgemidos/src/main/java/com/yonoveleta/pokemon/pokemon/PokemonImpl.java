package com.yonoveleta.pokemon.pokemon;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.stat.Stat;
import com.yonoveleta.pokemon.stat.StatType;
import com.yonoveleta.pokemon.status.Status;
import com.yonoveleta.pokemon.type.Type;

public class PokemonImpl implements Pokemon {

	private static final PokemonUI pokemonUI = new PokemonUI();

	private String name;
	private int maxHp;
	private int hp;
	private List<Move> moveset;
	private PokemonState state;
	private List<Type> types;
	private int level;
	private boolean canMove = true;

	private List<Status> statuses = new ArrayList<Status>();
	private Map<StatType, Stat> stats;

	public PokemonImpl(String name, int maxHp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			List<Type> types) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.types = types;
		
		// initialize stats
		stats = new EnumMap<>(StatType.class);
        stats.put(StatType.ATTACK, new Stat(attack));
        stats.put(StatType.DEFENSE, new Stat(defense));
        stats.put(StatType.SPECIAL_ATTACK, new Stat(specialAttack));
        stats.put(StatType.SPECIAL_DEFENSE, new Stat(specialDefense));
        stats.put(StatType.SPEED, new Stat(speed));
        
		this.moveset = new ArrayList<Move>();
		this.state = PokemonState.NORMAL;
		this.level = 0;
		
		levelUp();
	}

	public PokemonImpl(String name, int maxHp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			List<Type> types, int level) {
		this(name, maxHp, attack, defense, specialAttack, specialDefense, speed, types);
		this.level = level;
		levelUp(level);
	}

	public PokemonImpl(String name, int maxHp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			int level, List<Type> types, List<Move> moveset) {
		this(name, maxHp, attack, defense, specialAttack, specialDefense, speed, types, level);
		this.moveset = new ArrayList<>(moveset);
	}

	public PokemonImpl(Pokemon other) {
		this(other.getName(), other.getHp(), other.getAttack(), other.getDefense(), other.getSpecialAttack(),
				other.getSpecialDefense(), other.getSpeed(), other.getTypes());
	}

	@Override
	public void takeDamage(int damage) {
		this.hp = Math.max(0, this.hp - damage);
	}

	@Override
	public void levelUp() {
		level++;
		learnMovesOnLevelUp();
	}
	
	public void levelUp(int levels) {
		for(int i = 0; i < levels; i++) {
			levelUp();
			if(getLevel() != 1) 
				pokemonUI.showLevelUpMessage(this);
		}
	}

	public void learnMove(Move newMove) {
		if(newMove == null) {
			return;
		}
		
	    if (movesKnown() == 4) {
	        handleMoveReplacement(newMove); // Separate move replacement logic
	    } else {
	        addMove(newMove); // Let Pokemon handle adding the move
	        
	        if(level != 1)
	        	pokemonUI.showMoveLearnedMessage(this, newMove); // Separate UI handling
	    }
	}
	
	private void handleMoveReplacement(Move newMove) {
	    pokemonUI.showCantLearnMoreMovesMessage(this, newMove);
	    int option = pokemonUI.askToForgetMove(newMove);

	    if (option == 0) {
	        replaceMove(newMove); // Let Pokemon handle move replacement
	    } else {
	        pokemonUI.showMoveNotLearned(newMove);
	    }
	}
	
	@Override
	public void replaceMove(Move newMove) {
		int option = pokemonUI.askForMoveToForget(this);
		pokemonUI.showMoveReplacedMessage(this, this.moveset.get(option), newMove);
		this.moveset.set(option, newMove);
	}

	@Override
	public void forgetMove(int move) {
		this.moveset.remove(move);
	}
	
	private void learnMovesOnLevelUp() {
	    /*List<MoveLevel> possibleMoves = movePoolRepository.getMovesForPokemon(name);

	    for (MoveLevel moveLevel : possibleMoves) {
	        if (moveLevel.getLevel() == level) {
	            
	            learnMove(moveLevel.getMove());
	        }
	    }*/
	}

	@Override
	public List<Move> getMoveset() {
		return new ArrayList<Move>(moveset);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public PokemonState getState() {
		return this.state;
	}

	@Override
	public List<Type> getTypes() {
		return this.types;
	}

	private void addMove(Move move) {
		this.moveset.add(move);
	}
	
	public void modifyStat(StatType statType, int amount) {
        stats.get(statType).modifyStage(amount);
    }

    public int getModifiedStat(StatType statType) {
        return stats.get(statType).getModifiedValue();
    }
    
    public void resetAllStats() {
        for (Stat stat : stats.values()) {
            stat.reset();
        }
    }

	public int getAttack() {
		return getModifiedStat(StatType.ATTACK);
	}

	public int getDefense() {
		return getModifiedStat(StatType.DEFENSE);
	}

	public int getSpecialAttack() {
		return getModifiedStat(StatType.SPECIAL_ATTACK);
	}

	public int getSpecialDefense() {
		return getModifiedStat(StatType.SPECIAL_DEFENSE);
	}

	public int getSpeed() {
		return getModifiedStat(StatType.SPEED);
	}

	public int getLevel() {
		return level;
	}

	@Override
	public int movesKnown() {
		return getMoveset().size();
	}

	@Override
	public void setState(PokemonState state) {
		this.state = state;
	}

	@Override
	public boolean hasMove(Move move) {
		return getMoveset().contains(move);
	}

	@Override
	public Move getMove(int move) {
		return this.moveset.get(move);
	}

	@Override
	public void addStatus(Status status) {
	    if (!hasStatus(status.getClass())) {
	        statuses.add(status);
	        status.onInit(this);
	    } 
	}
	
	public boolean hasStatus(Class<? extends Status> statusType) {
	    return statuses.stream().anyMatch(status -> status.getClass() == statusType);
	}

	public void removeStatus(Status status) {
	    statuses.remove(status);
	}

	@Override
	public void heal(int hp) {
		this.hp += hp;
	}

	@Override
	public double getMaxHP() {
		return this.maxHp;
	}

	@Override
	public boolean canMove() {
		return this.canMove;
	}

	@Override
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	@Override
	public List<Status> getStatuses() {
		return statuses;
	}

	@Override
	public boolean isFainted() {
		return state == PokemonState.FAINTED;
	}

}
