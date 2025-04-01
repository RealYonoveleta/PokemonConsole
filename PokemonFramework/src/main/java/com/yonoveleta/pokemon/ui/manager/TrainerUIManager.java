package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.ui.TrainerUI;
import com.yonoveleta.pokemon.ui.impl.DefaultTrainerUI;

public class TrainerUIManager extends AbstractUIManager<TrainerUI> {

	private static TrainerUIManager instance = new TrainerUIManager();
	
	private TrainerUIManager() {
		setUI(new DefaultTrainerUI());
	}
	
	public static TrainerUIManager getInstance() {
		return instance;
	}
	
}
