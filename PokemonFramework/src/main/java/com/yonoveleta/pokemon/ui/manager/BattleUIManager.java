package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.di.annotation.UIManagerType;
import com.yonoveleta.pokemon.ui.BattleUI;
import com.yonoveleta.pokemon.ui.impl.DefaultBattleUI;

@UIManagerType
public class BattleUIManager extends AbstractUIManager<BattleUI> {

	private static BattleUIManager instance = new BattleUIManager();

	private BattleUIManager() {
		setUI(new DefaultBattleUI());
	}
	
	public static BattleUIManager getInstance() {
		return instance;
	}

}
