package status;

import pokemon.Pokemon;

public class Paralysis extends AbstractStatus {

	public Paralysis() {
		super(Integer.MAX_VALUE);
	}
	
	@Override
	public void onInit(Pokemon pokemon) {
		System.out.printf("%n%s is paralyzed! It may not be able to move!", pokemon.getName());
	}
	
	@Override
	public void onDuringTurn(Pokemon pokemon) {
		expire(pokemon);
		
		if (Math.random() < 0.25) {
            pokemon.setCanMove(false);
            System.out.println(pokemon.getName() + " is paralyzed! It can't move!");
        }
	}
	
	@Override
	public void onEndOfTurn(Pokemon pokemon) {
		pokemon.setCanMove(true);
	}

	@Override
	public String getName() {
		return "Paralyzed";
	}

	@Override
	public Status createNewInstance() {
		return new Paralysis();
	}
	
	protected void onExpire(Pokemon pokemon) {
		statusUI.displayRemovedStatus(pokemon, this);
	}

}
