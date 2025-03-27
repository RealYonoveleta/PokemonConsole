package com.yonoveleta.pokemon.factory;

import com.yonoveleta.pokemon.battle.Battle;
import com.yonoveleta.pokemon.di.annotation.Factory;
import com.yonoveleta.pokemon.trainer.Trainer;

@Factory
public class BattleManager {
	
	private static final BattleManager INSTANCE = new BattleManager();

	private BattleManager() {}

	public static BattleManager getInstance() {
		return INSTANCE;
	}
	
	public void startBattle(Trainer player, Trainer rival) {
		new Battle(player, rival).start();
	}

}
