package parse.deserialize.effect;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import effect.Effect;
import effect.StatusEffect;
import registry.StatusRegistry;
import status.Status;

public class StatusEffectDeserializer implements JsonDeserializer<Effect> {
	   
	@Override
    public Effect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String statusType = jsonObject.get("status").getAsString();
        double chance = jsonObject.get("chance").getAsDouble();

        Class<? extends Status> statusClass = StatusRegistry.getStatusClass(statusType);

        try {
            Status status = statusClass.getDeclaredConstructor().newInstance();
            return new StatusEffect(status, chance);
        } catch (Exception e) {
            throw new JsonParseException("Failed to create StatusEffect instance", e);
        }
    }
	
}
