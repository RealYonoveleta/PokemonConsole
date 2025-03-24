package effect;

import annotation.EffectType;
import parse.deserialize.effect.HealingEffectDeserializer;
import pokemon.Pokemon;

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

