package com.yonoveleta.pokemon.parse;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yonoveleta.pokemon.effect.Effect;
import com.yonoveleta.pokemon.move.MoveImpl;
import com.yonoveleta.pokemon.parse.deserialize.EffectDeserializer;
import com.yonoveleta.pokemon.parse.deserialize.MoveDeserializer;
import com.yonoveleta.pokemon.parse.deserialize.PokemonDeserializer;
import com.yonoveleta.pokemon.parse.deserialize.StatusDeserializer;
import com.yonoveleta.pokemon.pokemon.PokemonImpl;
import com.yonoveleta.pokemon.status.Status;

public class GsonRegistry {
	
    private static final GsonRegistry INSTANCE = new GsonRegistry();
    private final GsonBuilder gsonBuilder;
    private Gson gson;

    private GsonRegistry() {
        gsonBuilder = new GsonBuilder();
        
        // Register base deserializers for your entities
        gsonBuilder.registerTypeAdapter(PokemonImpl.class, new PokemonDeserializer());
        gsonBuilder.registerTypeAdapter(MoveImpl.class, new MoveDeserializer());
        gsonBuilder.registerTypeAdapter(Status.class, new StatusDeserializer());
        gsonBuilder.registerTypeAdapter(Effect.class, new EffectDeserializer());
        
        gson = gsonBuilder.create();
    }

    public static GsonRegistry getInstance() {
        return INSTANCE;
    }

    public Gson getGson() {
        return gson;
    }

    public void registerTypeAdapter(Type type, Object typeAdapter) {
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
        gson = gsonBuilder.create(); // Recreate Gson instance after registration
    }
    
}


