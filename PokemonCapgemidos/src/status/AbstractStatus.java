package status;

import pokemon.Pokemon;

public abstract class AbstractStatus implements Status {
	
	protected static final StatusUI statusUI = new StatusUI();
	
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
	
	protected void onExpire(Pokemon pokemon) {
		// Override when need actions on status expiration
	}
	
	public void onInit(Pokemon pokemon) {} // Default: Do nothing
	
	public void onStartOfTurn(Pokemon pokemon) {} // Default: Do nothing
	 
	public void onDuringTurn(Pokemon pokemon) {} // Default: Do nothing
	 
	public void onEndOfTurn(Pokemon pokemon) {} // Default: Do nothing

}
