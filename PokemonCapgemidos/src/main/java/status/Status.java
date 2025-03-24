package status;

import pokemon.Pokemon;

public interface Status {
	
	void onInit(Pokemon pokemon);
	
	void onStartOfTurn(Pokemon pokemon);
	
    void onDuringTurn(Pokemon pokemon);
    
    void onEndOfTurn(Pokemon pokemon);
	
	String getName();
	
	int getTurnsRemaining();
	
	void resetDuration();
	
	boolean isExpired();
	
	void decrementTurnsRemaining();
	
	Status createNewInstance();

}
