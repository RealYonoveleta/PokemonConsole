package com.yonoveleta.pokemon.status;

import com.yonoveleta.pokemon.annotation.StatusType;
import com.yonoveleta.pokemon.io.ConsoleColor;
import com.yonoveleta.pokemon.io.ConsoleHandler;
import com.yonoveleta.pokemon.parse.deserialize.StatusDeserializer;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.stat.StatType;

@StatusType(name = "burn", deserializer = StatusDeserializer.class)
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
