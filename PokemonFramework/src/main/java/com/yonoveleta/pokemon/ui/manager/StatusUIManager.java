package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.di.annotation.UIManagerType;
import com.yonoveleta.pokemon.ui.StatusUI;
import com.yonoveleta.pokemon.ui.impl.DefaultStatusUI;

@UIManagerType
public class StatusUIManager extends AbstractUIManager<StatusUI> {
	
	private static StatusUIManager instance = new StatusUIManager();
	
	private StatusUIManager() {
		setUI(new DefaultStatusUI());
	}
	
	public static StatusUIManager getInstance() {
		return instance;
	}
	
}
