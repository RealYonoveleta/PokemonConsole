package com.yonoveleta.pokemon.parse.deserialize;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.pokemon.PokemonImpl;
import com.yonoveleta.pokemon.type.Type;

public class PokemonDeserializer implements JsonDeserializer<Pokemon> {

	@Override
	public Pokemon deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		
		String name = jsonObject.get("name").getAsString();
		int hp = jsonObject.get("hp").getAsInt();
		int attack = jsonObject.get("attack").getAsInt();
		int defense = jsonObject.get("defense").getAsInt();
		int specialAttack = jsonObject.get("specialAttack").getAsInt();
		int specialDefense = jsonObject.get("specialDefense").getAsInt();
		int speed = jsonObject.get("speed").getAsInt();
		
		// Deserialize types
        JsonArray typesArray = jsonObject.getAsJsonArray("types");
        List<Type> types = new ArrayList<>();
        for (int i = 0; i < typesArray.size(); i++) {
            String typeName = typesArray.get(i).getAsString().toUpperCase();
            Type type = Type.valueOf(typeName);
            types.add(type);
        }
        
        return new PokemonImpl(name, hp, attack, defense, specialAttack, specialDefense, speed, types);
	}

}
