package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.di.annotation.UIManagerType;
import com.yonoveleta.pokemon.ui.MoveUI;
import com.yonoveleta.pokemon.ui.impl.DefaultMoveUI;

@UIManagerType
public class MoveUIManager extends AbstractUIManager<MoveUI> {

	private static MoveUIManager instance = new MoveUIManager();
	
	private MoveUIManager() {
		setUI(new DefaultMoveUI());
	}
	
	public static MoveUIManager getInstance() {
		return instance;
	}
	
}
