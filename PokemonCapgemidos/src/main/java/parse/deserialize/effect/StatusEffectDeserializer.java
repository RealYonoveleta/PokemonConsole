package parse.deserialize.effect;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import effect.Effect;
import effect.StatusEffect;
import status.Status;

public class StatusEffectDeserializer implements JsonDeserializer<Effect> {
	   
	@Override
    public Effect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject statusObj = jsonObject.get("status").getAsJsonObject();
        double chance = jsonObject.get("chance").getAsDouble();

        Status status = context.deserialize(statusObj, Status.class);

        return new StatusEffect(status, chance);
    }
	
}
