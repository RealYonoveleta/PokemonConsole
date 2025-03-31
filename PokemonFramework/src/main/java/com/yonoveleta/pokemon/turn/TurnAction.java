package com.yonoveleta.pokemon.turn;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;
import com.yonoveleta.pokemon.ui.impl.DefaultPokemonUI;

public class TurnAction {
	
	private static final DefaultPokemonUI pokemonUI = new DefaultPokemonUI();;
	
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
