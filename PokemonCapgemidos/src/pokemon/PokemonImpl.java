package pokemon;

import java.util.List;

import move.Move;

public class PokemonImpl implements Pokemon {
	
	private String name;
	private int hp;
	private List<Move> moveset;
	private PokemonState state;
	
	public PokemonImpl(String name, int hp, List<Move> moveset) {
		this.name = name;
		this.hp = hp;
		this.moveset = moveset;
		this.state = null;
	}

	@Override
	public void showMoveset() {
		int option = 1;
		for(Move move : this.moveset) {
			System.out.printf("%d.-%s", option, move.toString());
			option++;
		}
	}

	@Override
	public void useMove(int move, Pokemon target) {
		this.moveset.get(move).use(this, target);
	}
	
	@Override
	public void takeDamage(int damage) {
		this.hp -= damage;
		if(this.hp <= 0) {
			this.hp = 0;
			this.state = PokemonState.FAINTED;
		}
	}

	@Override
	public List<Move> getMoveset() {
		return moveset;
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

}
