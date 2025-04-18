package com.yonoveleta.pokemon.status;

import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.ui.StatusUI;
import com.yonoveleta.pokemon.ui.manager.StatusUIManager;

public abstract class AbstractStatus implements Status {
	
	protected StatusUI statusUI = StatusUIManager.getInstance().getUI();
	protected int duration;      // Total duration of the status effect
    protected int turnsRemaining; // How many turns are left
    
    protected AbstractStatus(int duration) {
    	this.duration = duration;
    	this.turnsRemaining = duration;
    }
	
	@Override
	public int getTurnsRemaining() {
		return turnsRemaining;
	}

	@Override
	public void resetDuration() {
		this.turnsRemaining = this.duration;
	}
	
	@Override
	public boolean isExpired() {
		return turnsRemaining <= 0;
	}
	
	@Override
	public void decrementTurnsRemaining() {
		turnsRemaining--;
	}
	
	protected void expire(Pokemon pokemon) {
		if(!isExpired()) return;
		onExpire(pokemon);
		pokemon.removeStatus(this);
	}
	
	@Override
	public void setStatusUI(StatusUI statusUI) {
		this.statusUI = statusUI;
	}
	
	protected void onExpire(Pokemon pokemon) {
		// Override when need actions on status expiration
	}
	
	public void onInit(Pokemon pokemon) {} // Default: Do nothing
	
	public void onStartOfTurn(Pokemon pokemon) {} // Default: Do nothing
	 
	public void onDuringTurn(Pokemon pokemon) {} // Default: Do nothing
	 
	public void onEndOfTurn(Pokemon pokemon) {} // Default: Do nothing

}
