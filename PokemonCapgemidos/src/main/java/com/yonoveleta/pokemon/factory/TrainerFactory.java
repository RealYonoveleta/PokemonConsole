package com.yonoveleta.pokemon.factory;

import java.util.List;

import com.yonoveleta.pokemon.di.annotation.Factory;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.AITrainer;
import com.yonoveleta.pokemon.trainer.PlayerTrainer;
import com.yonoveleta.pokemon.trainer.Trainer;

@Factory
public class TrainerFactory {
	
	private static final TrainerFactory INSTANCE = new TrainerFactory();

	private TrainerFactory() {}

	public static TrainerFactory getInstance() {
		return INSTANCE;
	}
	
	public Trainer createPlayerTrainer(String name, List<Pokemon> pokemons) {
		return new PlayerTrainer(name, pokemons);
	}
	
	public Trainer createAITrainer(String name, List<Pokemon> pokemons) {
		return new AITrainer(name, pokemons);
	}

}
