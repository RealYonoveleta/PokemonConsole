package com.yonoveleta.pokemon.parse.deserialize;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yonoveleta.pokemon.effect.Effect;
import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.move.MoveImpl;
import com.yonoveleta.pokemon.move.MoveType;
import com.yonoveleta.pokemon.type.Type;

public class MoveDeserializer implements JsonDeserializer<Move> {
    
	@Override
    public Move deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        String name = jsonObject.get("name").getAsString();
        int power = jsonObject.get("power").getAsInt();
        int pps = jsonObject.get("pp").getAsInt();
        String type = jsonObject.get("type").getAsString().toUpperCase();
        String category = jsonObject.get("category").getAsString().toUpperCase();
        
        // Deserialize effects
        JsonArray effectsArray = jsonObject.getAsJsonArray("effects");
        List<Effect> effects = new ArrayList<>();
        for (JsonElement effectElement : effectsArray) {
            Effect effect = context.deserialize(effectElement, Effect.class);
            effects.add(effect);
        }
        
        return new MoveImpl(name, power, pps, Type.valueOf(type), MoveType.valueOf(category), effects);
    }

}

