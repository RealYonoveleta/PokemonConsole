package status;

import io.ConsoleColor;
import io.ConsoleHandler;
import pokemon.Pokemon;
import stat.StatType;

public class Burn extends AbstractStatus {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();

	public Burn() {
		super(Integer.MAX_VALUE);
	}

	@Override
	public void onInit(Pokemon pokemon) {
		pokemon.modifyStat(StatType.ATTACK, -2); // Reduce Attack by 50%
		System.out.printf("%n%s was burned!", pokemon.getName());
	}

	@Override
	public void onDuringTurn(Pokemon pokemon) {
		expire(pokemon);
	}

	@Override
	public void onEndOfTurn(Pokemon pokemon) {
		int damage = (int) Math.max(1, Math.round(pokemon.getMaxHP() / 16.0f));
		pokemon.takeDamage(damage);
		console.displayMessage("%n%s is burned! It takes %d damage!", ConsoleColor.RED, pokemon.getName(), damage);
	}

	@Override
	public String getName() {
		return "Burned";
	}

	@Override
	public Status createNewInstance() {
		return new Burn();
	}

	@Override
	protected void onExpire(Pokemon pokemon) {
		statusUI.displayRemovedStatus(pokemon, this);
	}

}
