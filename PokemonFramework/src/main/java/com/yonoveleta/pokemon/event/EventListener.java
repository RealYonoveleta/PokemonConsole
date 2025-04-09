package com.yonoveleta.pokemon.event;

@FunctionalInterface
public interface EventListener<T extends Event> {
    void handle(T event);
}
