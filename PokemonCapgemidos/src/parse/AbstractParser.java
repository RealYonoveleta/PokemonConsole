package parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import effect.Effect;
import effect.HealingEffect;
import effect.StatusEffect;
import move.MoveImpl;
import parse.deserialize.EffectDeserializer;
import parse.deserialize.MoveDeserializer;
import parse.deserialize.effect.HealingEffectDeserializer;
import parse.deserialize.effect.StatusEffectDeserializer;

public abstract class AbstractParser<T> implements Parser<T> {

    protected Gson gson;

    public AbstractParser() {
        this.gson = new GsonBuilder()
        		.registerTypeAdapter(StatusEffect.class, new StatusEffectDeserializer())
        		.registerTypeAdapter(HealingEffect.class, new HealingEffectDeserializer())
                .registerTypeAdapter(Effect.class, new EffectDeserializer())
                .registerTypeAdapter(MoveImpl.class, new MoveDeserializer())
                .create();
    }

}

