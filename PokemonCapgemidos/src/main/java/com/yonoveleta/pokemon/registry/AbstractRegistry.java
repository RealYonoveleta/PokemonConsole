package com.yonoveleta.pokemon.registry;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializer;
import com.yonoveleta.pokemon.parse.GsonRegistry;

public abstract class AbstractRegistry<T> {
	
    protected final Map<String, Class<? extends T>> registryMap = new HashMap<>();
    protected final Map<Class<? extends T>, JsonDeserializer<? extends T>> deserializerMap = new HashMap<>();
    
    public void register(String name, Class<? extends T> clazz, JsonDeserializer<? extends T> deserializer) {
        // Ensure the class is not already registered
        if (!registryMap.containsKey(name)) {
            registryMap.put(name, clazz);
            deserializerMap.put(clazz, deserializer);

            // Register the deserializer with Gson
            GsonRegistry.getInstance().registerTypeAdapter(clazz, deserializer);
        } 
    }

    public Class<? extends T> get(String name) {
        return registryMap.get(name);
    }
    
}

