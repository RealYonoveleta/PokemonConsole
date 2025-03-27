package com.yonoveleta.pokemon.parse.deserialize.effect;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yonoveleta.pokemon.effect.Effect;
import com.yonoveleta.pokemon.effect.HealingEffect;

public class HealingEffectDeserializer implements JsonDeserializer<Effect> {
	
	@Override
    public Effect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        double percentage = jsonObject.get("percentage").getAsDouble();

        try {
            return new HealingEffect(percentage);
        } catch (Exception e) {
            throw new JsonParseException("Failed to create HealingEffect instance", e);
        }
    }

}
