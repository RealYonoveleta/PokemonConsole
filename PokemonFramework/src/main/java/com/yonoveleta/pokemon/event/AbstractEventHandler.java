package com.yonoveleta.pokemon.event;

public abstract class AbstractEventHandler<T> implements EventHandler<T> {
	
	protected final T entity; 

	protected AbstractEventHandler(T entity) {
		this.entity = entity;
		registerListeners();
	}
	
	protected <E extends Event> void onEvent(Class<E> eventType, EventListener<E> listener) {
        EventDispatcher.registerListener(entity, eventType, listener);
    }

}
