package status;

import pokemon.Pokemon;

public class Flinch extends AbstractStatus {
	
    public Flinch() {
		super(1);
	}
    
    @Override
    public void onInit(Pokemon pokemon) {
    	pokemon.setCanMove(false);
    	System.out.printf("The impact caused %s to flinch", pokemon.getName());
    }
    
    @Override
    public void onDuringTurn(Pokemon pokemon) {
    	System.out.printf("%n%s flinched and couldn't move!", pokemon.getName());
    }
    
    @Override
    public void onEndOfTurn(Pokemon pokemon) {
    	decrementTurnsRemaining();
    	expire(pokemon);
    }

    @Override
    public String getName() {
        return "Flinched";
    }
    
    @Override
    protected void onExpire(Pokemon pokemon) {
		pokemon.setCanMove(true);
	}

	@Override
	public Status createNewInstance() {
		return new Flinch();
	}

}

