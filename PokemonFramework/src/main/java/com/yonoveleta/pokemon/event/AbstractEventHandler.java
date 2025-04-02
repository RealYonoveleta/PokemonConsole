package com.yonoveleta.pokemon.event;

public abstract class AbstractEventHandler<T> implements EventHandler<T> {
	
	protected final T entity; 

	protected AbstractEventHandler(T entity) {
		this.entity = entity;
		registerListeners();
	}

}
