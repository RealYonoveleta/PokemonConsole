package com.yonoveleta.pokemon.parse.deserialize;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yonoveleta.pokemon.effect.Effect;
import com.yonoveleta.pokemon.registry.EffectRegistry;

public class EffectDeserializer implements JsonDeserializer<Effect> {
   
	@Override
    public Effect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String effectType = jsonObject.get("type").getAsString();
        JsonObject effect = jsonObject.get("effect").getAsJsonObject();

        // Retrieve class and creator from registry
        Class<? extends Effect> effectClass = EffectRegistry.getInstance().get(effectType);
        
        if (effectClass == null) {
            throw new JsonParseException("Unknown effect type: " + effectType);
        }

        return context.deserialize(effect, effectClass);
    }
	
}
