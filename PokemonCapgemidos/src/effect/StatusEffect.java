package effect;

import pokemon.Pokemon;
import status.Status;

public class StatusEffect implements Effect {
	
    private final Status status;

    public StatusEffect(Status status) {
        this.status = status;
    }

    @Override
    public void apply(Pokemon user, Pokemon target) {
        target.addStatus(status);
    }
    
}

