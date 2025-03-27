package com.yonoveleta.pokemon.effect;

import java.util.Random;

import com.yonoveleta.pokemon.annotation.EffectType;
import com.yonoveleta.pokemon.parse.deserialize.effect.StatusEffectDeserializer;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.status.Status;

@EffectType(name = "status", deserializer = StatusEffectDeserializer.class)
public class StatusEffect implements Effect {

	private static final Random random = new Random();
	
    private final Status status;
    private double chance;

    public StatusEffect(Status status, double chance) {
        this.status = status;
        this.chance = chance;
    }

    @Override
    public void apply(Pokemon user, Pokemon target) {
    	if (random.nextDouble() < chance) { // Random value between 0.0 and 1.0
            target.addStatus(status.createNewInstance());
    	}
    }
    
}

