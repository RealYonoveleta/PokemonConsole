package com.yonoveleta.pokemon.trainer;

import java.util.List;
import java.util.function.Consumer;

import com.yonoveleta.pokemon.event.EventDispatcher;
import com.yonoveleta.pokemon.event.trainer.ChooseMoveEvent;
import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonState;

public class PlayerTrainer extends AbstractTrainer {

	public PlayerTrainer(String name, List<Pokemon> pokemons) {
		super(name, pokemons);
	}
	
	public void setActivePokemon() {
		trainerUI.setActivePokemon(this);
	}
	
	public void setActivePokemon(int option) {
		if(!(getPokemons().get(option).getState() == PokemonState.FAINTED))
			this.currentPokemon = option;
		
		else setActivePokemon();
	}

	@Override
	public void chooseMove(Consumer<Move> callback) {
		EventDispatcher.dispatchTo(this, new ChooseMoveEvent(this, move -> {
            Move chosenMove = getCurrentPokemon().getMove(move);
            callback.accept(chosenMove);
        }));
	}

}
