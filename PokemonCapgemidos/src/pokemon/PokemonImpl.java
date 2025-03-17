package pokemon;

import java.util.ArrayList;
import java.util.List;

import move.Move;
import type.Type;

public class PokemonImpl implements Pokemon {
	
	private static final PokemonUI pokemonUI = new PokemonUI();

	private String name;
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

	public PokemonImpl(String name, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			List<Type> types) {
		this.name = name;
		this.hp = hp;
		this.types = types;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.level = 1;
		this.moveset = new ArrayList<Move>();
		this.state = PokemonState.NORMAL;
	}

	public PokemonImpl(String name, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			List<Type> types, int level) {
		this(name, hp, attack, defense, specialAttack, specialDefense, speed, types);
		this.level = level;
	}

	public PokemonImpl(String name, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed,
			int level, List<Type> types, List<Move> moveset) {
		this(name, hp, attack, defense, specialAttack, specialDefense, speed, types, level);
		this.moveset = new ArrayList<>(moveset);
	}

	public PokemonImpl(Pokemon other) {
		this(other.getName(), other.getHp(), other.getAttack(), other.getDefense(), other.getSpecialAttack(),
				other.getSpecialDefense(), other.getSpeed(), other.getTypes());
	}

	

	@Override
	public boolean useMove(int move, Pokemon target) {
		return this.moveset.get(move).use(this, target);
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

}
