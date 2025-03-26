package com.yonoveleta.pokemon.effect;

import com.yonoveleta.pokemon.annotation.EffectType;
import com.yonoveleta.pokemon.parse.deserialize.effect.HealingEffectDeserializer;
import com.yonoveleta.pokemon.pokemon.Pokemon;

@EffectType(name = "heal", deserializer = HealingEffectDeserializer.class)
public class HealingEffect implements Effect {
    private final double healPercentage;

    public HealingEffect(double healPercentage) {
        this.healPercentage = healPercentage;
    }

    @Override
    public void apply(Pokemon user, Pokemon target) {
        user.heal((int) (user.getMaxHP() * healPercentage));
    }
}

