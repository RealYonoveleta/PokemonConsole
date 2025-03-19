package pokemon;

import java.util.ArrayList;
import java.util.List;

import move.Move;
import movepool.MoveLevel;
import movepool.MovePoolRepository;
import status.Status;
import type.Type;

public class PokemonImpl implements Pokemon {

	private static final PokemonUI pokemonUI = new PokemonUI();
	private static final MovePoolRepository movePoolRepository = MovePoolRepository.getInstance();

	private String name;
	private int maxHp;
	private int hp;
	private List<Move> moveset;
	private PokemonState state;
	private List<Type> types;
	private int attack;
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private int speed;
	private int level;
	
	List<Status> statuses = new ArrayList<Status>();

	public PokemonImpl(String name, int maxHp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			List<Type> types) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.types = types;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
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
		this.hp -= damage;
		if (this.hp <= 0) {
			this.hp = 0;
			this.state = PokemonState.FAINTED;
		}
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

	private void learnMovesOnLevelUp() {
		List<MoveLevel> possibleMoves = movePoolRepository.getMovesForPokemon(name);

		for (MoveLevel moveLevel : possibleMoves) {
			if (moveLevel.getLevel() == level) {
				pokemonUI.learnMove(this, moveLevel.getMove());
			}
		}
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

	@Override
	public void learnMove(Move move) {
		pokemonUI.learnMove(this, move);
	}

	public void addMove(Move move) {
		this.moveset.add(move);
	}

	@Override
	public void replaceMove(Move newMove) {
		pokemonUI.replaceMove(this, newMove);
	}

	@Override
	public void forgetMove(int move) {
		this.moveset.remove(move);
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public int getSpecialDefense() {
		return specialDefense;
	}

	public int getSpeed() {
		return speed;
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
	    if (!hasStatus(status)) {
	        statuses.add(status);
	    }
	}

	public void removeStatus(Status status) {
	    statuses.remove(status);
	}

	public boolean hasStatus(Status status) {
	    return statuses.contains(status);
	}

	@Override
	public void heal(int hp) {
		this.hp += hp;
	}

	@Override
	public double getMaxHP() {
		return this.maxHp;
	}

}
