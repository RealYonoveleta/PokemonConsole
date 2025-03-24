package parse;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import effect.Effect;
import move.MoveImpl;
import parse.deserialize.EffectDeserializer;
import parse.deserialize.MoveDeserializer;
import parse.deserialize.StatusDeserializer;
import status.Status;

public class GsonRegistry {
	
    private static final GsonRegistry INSTANCE = new GsonRegistry();
    private final GsonBuilder gsonBuilder;
    private Gson gson;

    private GsonRegistry() {
        gsonBuilder = new GsonBuilder();
        
        // Register base deserializers for your entities
        gsonBuilder.registerTypeAdapter(MoveImpl.class, new MoveDeserializer());
        gsonBuilder.registerTypeAdapter(Status.class, new StatusDeserializer());
        gsonBuilder.registerTypeAdapter(Effect.class, new EffectDeserializer());
        
        gson = gsonBuilder.create();
    }

    public static GsonRegistry getInstance() {
        return INSTANCE;
    }

    public Gson getGson() {
        return gson;
    }

    public void registerTypeAdapter(Type type, Object typeAdapter) {
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
        gson = gsonBuilder.create(); // Recreate Gson instance after registration
    }
    
}


