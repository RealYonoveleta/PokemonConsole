package com.yonoveleta.pokemon.registry;

import com.google.gson.JsonDeserializer;

public interface Registry<T> {
	
	public void register(String name, Class<? extends T> clazz, JsonDeserializer<? extends T> deserializer);
	
	public Class<? extends T> get(String name);

}
