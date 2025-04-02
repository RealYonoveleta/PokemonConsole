package com.yonoveleta.pokemon.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventDispatcher {
	
    private static final Map<Class<? extends Event>, List<Consumer<Event>>> listeners = new HashMap<>();

    @SuppressWarnings("unchecked")
	public static <T extends Event> void registerListener(Class<T> eventType, Consumer<T> listener) {
        listeners.computeIfAbsent(eventType, _ -> new ArrayList<>()).add((Consumer<Event>) listener);
    }

    public static void dispatch(Event event) {
        List<Consumer<Event>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (Consumer<Event> listener : eventListeners) {
                listener.accept(event);
            }
        }
    }
    
}
