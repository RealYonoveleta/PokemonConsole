package com.yonoveleta.pokemon.env;

import java.util.HashMap;
import java.util.Map;

import com.yonoveleta.pokemon.di.annotation.GameContext;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;

@GameContext
public class GameState {
    private static final GameState INSTANCE = new GameState();

    private Map<String, Trainer> trainers = new HashMap<>();
    private Map<String, Pokemon> pokemons = new HashMap<>();

    private GameState() {}

    public static GameState getInstance() {
        return INSTANCE;
    }

    public Trainer getTrainer(String name) { return trainers.get(name); }
    public void addTrainer(String name, Trainer trainer) { trainers.put(name, trainer); }

    public Pokemon getPokemon(String name) { return pokemons.get(name); }
    public void addPokemon(String name, Pokemon pokemon) { pokemons.put(name, pokemon); }
}
