package com.yonoveleta.pokemon.event;

public interface Event {
	default long getTimestamp() {
		return System.currentTimeMillis();
	}
}
