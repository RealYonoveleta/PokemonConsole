package com.yonoveleta.pokemon.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventDispatcher {

    private static final Map<Object, Map<Class<?>, List<EventListener<?>>>> listenersByInstance = new ConcurrentHashMap<>();

    public static <T extends Event> void registerListener(Object owner, Class<T> eventType, EventListener<T> listener) {
        listenersByInstance
            .computeIfAbsent(owner, k -> new ConcurrentHashMap<>())
            .computeIfAbsent(eventType, k -> new ArrayList<>())
            .add(listener);
    }

    public static <T extends Event> void dispatchTo(Object target, T event) {
        Map<Class<?>, List<EventListener<?>>> listeners = listenersByInstance.get(target);
        if (listeners == null) return;

        List<EventListener<?>> handlers = listeners.get(event.getClass());
        if (handlers == null) return;

        for (EventListener<?> handler : handlers) {
            @SuppressWarnings("unchecked")
            EventListener<T> typed = (EventListener<T>) handler;
            typed.handle(event);
        }
    }

    public static void unregister(Object owner) {
        listenersByInstance.remove(owner);
    }
}
