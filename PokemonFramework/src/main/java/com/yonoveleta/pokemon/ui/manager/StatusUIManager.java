package com.yonoveleta.pokemon.ui.manager;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.status.Status;
import com.yonoveleta.pokemon.ui.StatusUI;
import com.yonoveleta.pokemon.ui.impl.DefaultStatusUI;

public class StatusUIManager implements StatusUI {

	private static StatusUIManager instance = new StatusUIManager();

    private StatusUI statusUI;  // The UI being used for all Pokemon

    private StatusUIManager() {
        this.statusUI = new DefaultStatusUI();
    }

    public static synchronized StatusUIManager getInstance() {
        return instance;
    }

    public void setStatusUI(StatusUI newUI) {
        this.statusUI = newUI;
    }

    public StatusUI getStatusUI() {
        return this.statusUI;
    }

	@Override
	public void displayRemovedStatus(Pokemon pokemon, Status status) {
		statusUI.displayRemovedStatus(pokemon, status);
	}
	
}
