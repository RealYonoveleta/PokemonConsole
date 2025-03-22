package turn;

import move.Move;
import pokemon.Pokemon;
import pokemon.PokemonUI;
import trainer.Trainer;

public class TurnAction {
	
	private static final PokemonUI pokemonUI = new PokemonUI();;
	
	private Trainer trainer;
    private Pokemon user;
    private Move move;
    private Pokemon target;
    private Trainer rival;

    public TurnAction(Trainer trainer, Pokemon user, Move move, Pokemon target, Trainer rival) {
    	this.trainer = trainer;
        this.user = user;
        this.move = move;
        this.target = target;
        this.rival = rival;
    }
    
    public void run() {
        if (!user.hasMove(move)) {
            pokemonUI.showMoveNotLearntMessage(user, move);
            return;
        }

        if (move.getPPs() <= 0) {
            pokemonUI.showNoPpsLeftMessage(user, move);
            return;
        }
        
        move.execute(user, target);
    }

	public Trainer getTrainer() {
		return trainer;
	}

	public Pokemon getUser() {
		return user;
	}

	public Pokemon getTarget() {
		return target;
	}

	public Move getMove() {
		return move;
	}

	public Trainer getRival() {
		return rival;
	}

}
