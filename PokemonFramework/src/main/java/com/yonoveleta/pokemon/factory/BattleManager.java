package com.yonoveleta.pokemon.factory;

import java.util.List;

import com.yonoveleta.pokemon.battle.SingleBattle;
import com.yonoveleta.pokemon.di.annotation.Factory;
import com.yonoveleta.pokemon.trainer.Trainer;

@Factory
public class BattleManager {
	
	private static final BattleManager INSTANCE = new BattleManager();

	private BattleManager() {}

	public static BattleManager getInstance() {
		return INSTANCE;
	}
	
	public List<Trainer> startBattle(Trainer player, Trainer rival) {
		return new SingleBattle(player, rival).start();
	}

}
