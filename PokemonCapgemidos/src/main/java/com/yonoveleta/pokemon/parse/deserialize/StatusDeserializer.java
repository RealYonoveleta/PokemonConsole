package com.yonoveleta.pokemon.parse.deserialize;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yonoveleta.pokemon.exception.StatusCreationException;
import com.yonoveleta.pokemon.registry.StatusRegistry;
import com.yonoveleta.pokemon.status.Status;

public class StatusDeserializer implements JsonDeserializer<Status> {

	@Override
	public Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
        String statusType = jsonObject.get("type").getAsString();
		
        Class<? extends Status> statusClass = StatusRegistry.getInstance().get(statusType);
        
        try {
			return createStatus(statusClass);
		} catch (StatusCreationException e) {
			e.printStackTrace();
		}
        
		return null;
	}
	
	private Status createStatus(Class<? extends Status> statusClass) throws StatusCreationException {
	    try {
	        return statusClass.getDeclaredConstructor().newInstance();
	    } catch (ReflectiveOperationException e) {
	        throw new StatusCreationException("Failed to create status instance", e);
	    }
	}

}
